package ru.rmntim.web.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RangeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Range {
    String message() default "Value is out of range";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    double min() default Double.MIN_VALUE;
    double max() default Double.MAX_VALUE;
}
