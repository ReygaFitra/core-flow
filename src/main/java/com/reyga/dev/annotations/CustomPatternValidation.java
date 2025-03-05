package com.reyga.dev.annotations;

import com.reyga.dev.utils.validations.CustomPatternValidationProcessor;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * The interface Onboard custom pattern.
 */
@Documented
@Constraint(validatedBy = CustomPatternValidationProcessor.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomPatternValidation {

    /**
     * Message string.
     *
     * @return the string
     */
    String message() default "";

    /**
     * Field name string.
     *
     * @return the string
     */
    String fieldName() default "";

    /**
     * Regex string.
     *
     * @return the string
     */
    String regex();

    /**
     * Groups class [ ].
     *
     * @return the class [ ]
     */
    Class<?>[] groups() default {};

    /**
     * Payload class [ ].
     *
     * @return the class [ ]
     */
    Class<? extends Payload>[] payload() default {};
}
