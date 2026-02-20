package org.example.dto

import java.io.Serializable
import javax.annotation.PostConstruct
import javax.enterprise.context.RequestScoped
import javax.inject.Named

@Named("pointRequestDto")
@RequestScoped
open class PointRequestDto : Serializable {
    companion object {
        private const val serialVersionUID: Long = 1L
    }

    private var _x: Double? = null
    private var _y: Double? = null
    private var _r: Double? = 2.0

    open var x: Double?
        get() = _x
        set(value) {
            _x = value
        }

    open var y: Double?
        get() = _y
        set(value) {
            _y = value
        }

    open var r: Double?
        get() = _r
        set(value) {
            _r = value
        }

    constructor()

    constructor(x: Double?, y: Double?, r: Double?) {
        this.x = x
        this.y = y
        this.r = r
    }

    @PostConstruct
    open fun init() {
        if (r == null) {
            r = 2.0
        }
    }

    override fun toString(): String {
        return "PointRequestDto(x=$x, y=$y, r=$r)"
    }
}

