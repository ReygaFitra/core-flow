package com.reyga.dev.annotations;

import com.reyga.dev.utils.validations.StringValidationProcessor;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StringValidationProcessor.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StringValidation {
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
     * Min int.
     *
     * @return the int
     */
    int min() default Integer.MIN_VALUE;

    /**
     * Max int.
     *
     * @return the int
     */
    int max() default Integer.MAX_VALUE;

    /**
     * Not null boolean.
     *
     * @return the boolean
     */
    boolean notNull() default false;

    /**
     * Not blank boolean.
     *
     * @return the boolean
     */
    boolean notBlank() default false;

    /**
     * Email boolean.
     *
     * @return the boolean
     */
    boolean email() default false;

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
