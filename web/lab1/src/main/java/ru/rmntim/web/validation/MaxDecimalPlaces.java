package ru.rmntim.web.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MaxDecimalPlacesValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxDecimalPlaces {
    String message() default "Too many decimal places";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int value() default 6;
}

