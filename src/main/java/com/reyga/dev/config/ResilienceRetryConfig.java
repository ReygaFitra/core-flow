package com.reyga.dev.config;

import com.reyga.dev.config.properties.CustomResilienceRetryConfigProperties;
import io.github.resilience4j.retry.RetryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.time.Duration;

@Configuration
@Import(CustomResilienceRetryConfigProperties.class)
public class ResilienceRetryConfig {

    @Bean
    public RetryConfig retryConfig(CustomResilienceRetryConfigProperties properties) {
        return RetryConfig.custom()
                .maxAttempts(properties.getMaxAttempts())
                .waitDuration(Duration.ofSeconds(properties.getWaitDurationSeconds()))
                .retryExceptions(properties.getRetryExceptionClass())
                .build();
    }
}
