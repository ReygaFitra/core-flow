package com.reyga.dev.annotations;

import com.reyga.dev.config.ResilienceCircuitBreakerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ResilienceCircuitBreakerConfig.class)
public @interface EnableResilienceCircuitBreakerCustomProperties {
}
