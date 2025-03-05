package com.reyga.dev.annotations;

import com.reyga.dev.utils.validations.MultipartValidationProcessor;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * The interface Onboard multipart validation.
 */
@Documented
@Constraint(validatedBy = MultipartValidationProcessor.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MultipartValidation {

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
     * Required boolean.
     *
     * @return the boolean
     */
    boolean required() default false;

    /**
     * Max size long.
     *
     * @return the long
     */
    long maxSize() default -1;

    /**
     * Allowed types string [ ].
     *
     * @return the string [ ]
     */
    String[] allowedTypes() default {};

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
