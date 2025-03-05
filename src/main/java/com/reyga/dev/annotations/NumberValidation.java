package com.reyga.dev.annotations;

import com.reyga.dev.utils.validations.NumberValidationProcessor;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NumberValidationProcessor.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberValidation {

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
     * Min long.
     *
     * @return the long
     */
    long min() default Long.MIN_VALUE;

    /**
     * Max long.
     *
     * @return the long
     */
    long max() default Long.MAX_VALUE;

    /**
     * Integer int.
     *
     * @return the int
     */
    int integer() default Integer.MAX_VALUE;

    /**
     * Fraction int.
     *
     * @return the int
     */
    int fraction() default Integer.MAX_VALUE;

    /**
     * Not null boolean.
     *
     * @return the boolean
     */
    boolean notNull() default false;

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
