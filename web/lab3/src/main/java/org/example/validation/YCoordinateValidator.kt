package org.example.validation

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
open class YCoordinateValidator : CoordinateValidator<Double?> {

    companion object {
        private const val MIN_Y = -2.0
        private const val MAX_Y = 2.0
    }

    @Throws(ValidationException::class)
    override fun validate(value: Double?) {
        if (value == null) {
            throw ValidationException("Координата Y не может быть null")
        }

        if (value.isNaN() || value.isInfinite()) {
            throw ValidationException("Координата Y должна быть числом")
        }

        if (value < MIN_Y || value > MAX_Y) {
            throw ValidationException(
                String.format("Координата Y должна быть в диапазоне [%.0f; %.0f]", MIN_Y, MAX_Y)
            )
        }

        if (value % 1 != 0.0) {
            throw ValidationException("Координата Y должна быть целым числом")
        }
    }
}

