package com.reyga.dev.config;

import com.reyga.dev.config.properties.CustomResilienceRateLimiterConfigProperties;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.time.Duration;

@Configuration
@Import(CustomResilienceRateLimiterConfigProperties.class)
public class ResilienceRateLimiterConfig {

    @Bean
    public RateLimiterConfig rateLimiterConfig(CustomResilienceRateLimiterConfigProperties properties) {
        return RateLimiterConfig.custom()
                .limitForPeriod(properties.getLimit())
                .limitRefreshPeriod(Duration.ofSeconds(properties.getRefresh()))
                .timeoutDuration(Duration.ofSeconds(properties.getTimeout()))
                .build();
    }

}
