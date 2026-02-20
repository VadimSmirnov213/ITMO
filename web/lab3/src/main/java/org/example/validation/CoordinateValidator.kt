package org.example.validation

interface CoordinateValidator<T> {
    @Throws(ValidationException::class)
    fun validate(value: T)
}

