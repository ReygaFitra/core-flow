package com.reyga.dev.config;

import com.reyga.dev.config.properties.CustomResilienceCircuitBreakerConfigProperties;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.time.Duration;

@Configuration
@Import(CustomResilienceCircuitBreakerConfigProperties.class)
public class ResilienceCircuitBreakerConfig {

    @Bean
    public CircuitBreakerConfig circuitBreakerConfig(CustomResilienceCircuitBreakerConfigProperties properties) {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(properties.getFailureRateThreshold())
                .slowCallRateThreshold(properties.getSlowCallRateThreshold())
                .slowCallDurationThreshold(Duration.ofSeconds(properties.getSlowCallDurationThresholdSeconds()))
                .minimumNumberOfCalls(properties.getMinimumNumberOfCalls())
                .slidingWindowSize(properties.getSlidingWindowSize())
                .permittedNumberOfCallsInHalfOpenState(properties.getPermittedNumberOfCallsInHalfOpenState())
                .waitDurationInOpenState(Duration.ofSeconds(properties.getSlowCallDurationThresholdSeconds()))
                .automaticTransitionFromOpenToHalfOpenEnabled(properties.isAutomaticTransitionFromOpenToHalfOpenEnabled())
                .build();
    }
}
