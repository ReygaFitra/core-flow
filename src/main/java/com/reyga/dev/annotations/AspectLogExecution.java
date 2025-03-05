package com.reyga.dev.annotations;

import java.lang.annotation.*;

/**
 * The interface Aspect log execution.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AspectLogExecution {
}
