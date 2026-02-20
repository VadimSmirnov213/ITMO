package ru.rmntim.web.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class MaxDecimalPlacesValidator implements ConstraintValidator<MaxDecimalPlaces, String> {
    private int maxPlaces;

    @Override
    public void initialize(MaxDecimalPlaces constraintAnnotation) {
        this.maxPlaces = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        try {
            BigDecimal bd = new BigDecimal(value);
            return bd.scale() <= maxPlaces;
        } catch (NumberFormatException e) {
            return false; 
        }
    }
}

