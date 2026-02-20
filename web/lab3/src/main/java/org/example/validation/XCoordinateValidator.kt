package org.example.validation

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
open class XCoordinateValidator : CoordinateValidator<Double?> {

    companion object {
        private const val MIN_X = -3.0
        private const val MAX_X = 5.0
    }

    @Throws(ValidationException::class)
    override fun validate(value: Double?) {
        if (value == null) {
            throw ValidationException("Координата X не может быть null")
        }

        if (value.isNaN() || value.isInfinite()) {
            throw ValidationException("Координата X должна быть числом")
        }

        if (value < MIN_X || value > MAX_X) {
            throw ValidationException(
                String.format("Координата X должна быть в диапазоне [%.1f; %.1f]", MIN_X, MAX_X)
            )
        }
    }
}

