package org.example.validation

import javax.enterprise.context.ApplicationScoped
import kotlin.math.abs

@ApplicationScoped
open class RadiusValidator : CoordinateValidator<Double?> {

    companion object {
        private const val MIN_R = 1.0
        private const val MAX_R = 4.0
        private const val STEP = 0.25
        private const val EPSILON = 0.001
    }

    @Throws(ValidationException::class)
    override fun validate(value: Double?) {
        if (value == null) {
            throw ValidationException("Радиус R не может быть null")
        }

        if (value.isNaN() || value.isInfinite()) {
            throw ValidationException("Радиус R должен быть числом")
        }

        if (value < MIN_R || value > MAX_R) {
            throw ValidationException(
                String.format("Радиус R должен быть в диапазоне [%.2f; %.2f]", MIN_R, MAX_R)
            )
        }

        val remainder = (value - MIN_R) % STEP
        if (abs(remainder) > EPSILON && abs(remainder - STEP) > EPSILON) {
            throw ValidationException(
                String.format("Радиус R должен быть кратен %.2f", STEP)
            )
        }
    }
}

