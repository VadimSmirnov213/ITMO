package org.example.validation

import java.io.Serializable

class ValidationException : Exception, Serializable {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
    
    companion object {
        private const val serialVersionUID: Long = 1L
    }
}

