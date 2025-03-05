package com.reyga.dev.annotations;

import java.lang.annotation.*;

/**
 * The interface Audit trail.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditTrail {
    /**
     * Activity type string.
     *
     * @return the string
     */
    String activityType();

    /**
     * Skip get additional data boolean.
     *
     * @return the boolean
     */
    boolean skipGetAdditionalData() default false;
}
