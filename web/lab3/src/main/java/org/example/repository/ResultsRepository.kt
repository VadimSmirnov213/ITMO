package org.example.repository

import org.example.entity.PointEntity
import org.example.exception.RepositoryException
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import javax.enterprise.context.ApplicationScoped
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence
import javax.persistence.TypedQuery

@ApplicationScoped
open class ResultsRepository {

    companion object {
        private const val PERSISTENCE_UNIT_NAME = "lab3-persistence-unit"
    }

    private var emf: EntityManagerFactory? = null
    private var em: EntityManager? = null

    @PostConstruct
    open fun init() {
        try {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
            em = emf?.createEntityManager()
        } catch (e: Exception) {
            throw RepositoryException("Ошибка инициализации базы данных: ${e.message}", e)
        }
    }

    @PreDestroy
    open fun cleanup() {
        em?.takeIf { it.isOpen }?.close()
        emf?.takeIf { it.isOpen }?.close()
    }

    private fun getEntityManager(): EntityManager {
        return if (em != null && em?.isOpen == true) {
            em!!
        } else {
            throw RepositoryException("База данных недоступна. EntityManager не инициализирован или закрыт.")
        }
    }

    open fun save(point: PointEntity): PointEntity {
        val entityManager = getEntityManager()
        
        try {
            entityManager.transaction.begin()
            val result = if (point.id == null) {
                entityManager.persist(point)
                point
            } else {
                entityManager.merge(point)
            }
            entityManager.transaction.commit()
            return result
        } catch (e: RepositoryException) {
            throw e
        } catch (e: Exception) {
            if (entityManager.transaction.isActive) {
                entityManager.transaction.rollback()
            }
            throw RepositoryException("Ошибка сохранения точки в базу данных: ${e.message}", e)
        }
    }

    open fun findAll(): List<PointEntity> {
        val entityManager = getEntityManager()
        
        try {
            val query: TypedQuery<PointEntity> = entityManager.createQuery(
                "SELECT p FROM PointEntity p ORDER BY p.timestamp DESC",
                PointEntity::class.java
            )
            return query.resultList
        } catch (e: RepositoryException) {
            throw e
        } catch (e: Exception) {
            throw RepositoryException("Ошибка чтения данных из базы данных: ${e.message}", e)
        }
    }

    open fun clear() {
        val entityManager = getEntityManager()
        
        try {
            entityManager.transaction.begin()
            entityManager.createQuery("DELETE FROM PointEntity").executeUpdate()
            entityManager.transaction.commit()
        } catch (e: RepositoryException) {
            throw e
        } catch (e: Exception) {
            if (entityManager.transaction.isActive) {
                entityManager.transaction.rollback()
            }
            throw RepositoryException("Ошибка очистки базы данных: ${e.message}", e)
        }
    }

    open fun count(): Int {
        val entityManager = getEntityManager()
        
        try {
            val query: TypedQuery<Long> = entityManager.createQuery(
                "SELECT COUNT(p) FROM PointEntity p",
                Long::class.java
            )
            return query.singleResult.toInt()
        } catch (e: RepositoryException) {
            throw e
        } catch (e: Exception) {
            throw RepositoryException("Ошибка подсчета записей в базе данных: ${e.message}", e)
        }
    }
}

