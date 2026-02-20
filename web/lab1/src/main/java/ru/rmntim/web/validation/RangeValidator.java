package ru.rmntim.web.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class RangeValidator implements ConstraintValidator<Range, String> {
    private double min;
    private double max;

    @Override
    public void initialize(Range constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        try {
            BigDecimal bd = new BigDecimal(value);
            double doubleValue = bd.doubleValue();
            return doubleValue >= min && doubleValue <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
