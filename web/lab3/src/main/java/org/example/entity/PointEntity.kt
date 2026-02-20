package org.example.entity

import java.io.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.*

@Entity
@Table(name = "POINT_RESULTS")
open class PointEntity : Serializable {
    companion object {
        private const val serialVersionUID: Long = 1L
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "point_seq")
    @SequenceGenerator(name = "point_seq", sequenceName = "POINT_SEQ", allocationSize = 1)
    @Column(name = "ID")
    var id: Long? = null

    @Column(name = "X_COORD", nullable = false)
    var x: Double? = null

    @Column(name = "Y_COORD", nullable = false)
    var y: Double? = null

    @Column(name = "R_VALUE", nullable = false)
    var r: Double? = null

    @Column(name = "HIT", nullable = false)
    var hit: Boolean? = null

    @Column(name = "TIMESTAMP", nullable = false)
    var timestamp: LocalDateTime? = null

    constructor() {
        this.timestamp = LocalDateTime.now()
    }

    constructor(x: Double?, y: Double?, r: Double?, hit: Boolean?) {
        this.x = x
        this.y = y
        this.r = r
        this.hit = hit
        this.timestamp = LocalDateTime.now()
    }

    fun getFormattedTime(): String {
        return timestamp?.format(DateTimeFormatter.ofPattern("HH:mm:ss")) ?: ""
    }

    fun getHitText(): String {
        val hitValue: Boolean? = hit
        return when {
            hitValue == null -> "Мимо"
            hitValue.equals(true) -> "Попадание"
            else -> "Мимо"
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        other as PointEntity
        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "PointEntity(id=$id, x=$x, y=$y, r=$r, hit=$hit, timestamp=$timestamp)"
    }
}

