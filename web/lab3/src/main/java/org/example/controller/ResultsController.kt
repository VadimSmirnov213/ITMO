package org.example.controller

import org.example.entity.PointEntity
import org.example.exception.ExceptionHandler
import org.example.repository.ResultsRepository
import javax.annotation.PostConstruct
import javax.enterprise.context.RequestScoped
import javax.inject.Inject
import javax.inject.Named

@Named("resultsController")
@RequestScoped
open class ResultsController {

    @Inject
    private lateinit var resultsRepository: ResultsRepository

    @Inject
    private lateinit var exceptionHandler: ExceptionHandler

    private var results: List<PointEntity> = emptyList()

    @PostConstruct
    open fun init() {
        try {
            loadResults()
        } catch (e: Exception) {
            exceptionHandler.handleAndAddToContext(e)
            results = emptyList()
        }
    }

    open fun loadResults() {
        try {
            results = resultsRepository.findAll()
        } catch (e: Exception) {
            exceptionHandler.handleAndAddToContext(e)
            results = emptyList()
        }
    }

    open fun getAll(): List<PointEntity> {
        return results
    }

    open fun clear() {
        try {
            resultsRepository.clear()
            loadResults()
        } catch (e: Exception) {
            exceptionHandler.handleAndAddToContext(e)
        }
    }

    open fun getCount(): Int {
        return results.size
    }
}

