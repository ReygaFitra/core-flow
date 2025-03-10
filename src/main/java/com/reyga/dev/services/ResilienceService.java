package com.reyga.dev.services;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.retry.RetryConfig;

import java.util.function.Supplier;

public interface ResilienceService {
    <T> T useRateLimitWithGlobalConfig(String rateLimitKey, String serviceName, Supplier<T> supplier);
    <T> T useRateLimitWithGlobalConfig(String rateLimitKey, String serviceName, Supplier<T> supplier, Supplier<T> fallbackSupplier);
    <T> T useCircuitBreakerWithGlobalConfig(String serviceName, Supplier<T> supplier);
    <T> T useCircuitBreakerWithGlobalConfig(String serviceName, Supplier<T> supplier, Supplier<T> fallbackSupplier);
    <T> T useRetryWithGlobalConfig(String serviceName, Supplier<T> supplier);
    <T> T useRetryWithGlobalConfig(String serviceName, Supplier<T> supplier, Supplier<T> fallbackSupplier);
    <T> T useCircuitBreakerAndRateLimiterWithGlobalConfig(String rateLimitKey, String serviceName, Supplier<T> supplier);
    <T> T useCircuitBreakerAndRateLimiterWithGlobalConfig(String rateLimitKey, String serviceName, Supplier<T> supplier, Supplier<T> fallbackSupplier);
    <T> T useRateLimitWithCustomConfig(RateLimiterConfig config, String rateLimitKey, String serviceName, Supplier<T> supplier);
    <T> T useRateLimitWithCustomConfig(RateLimiterConfig config, String rateLimitKey, String serviceName, Supplier<T> supplier, Supplier<T> fallbackSupplier);
    <T> T useCircuitBreakerWithCustomConfig(CircuitBreakerConfig config, String serviceName, Supplier<T> supplier);
    <T> T useCircuitBreakerWithCustomConfig(CircuitBreakerConfig config, String serviceName, Supplier<T> supplier, Supplier<T> fallbackSupplier);
    <T> T useRetryWithCustomConfig(RetryConfig config, String serviceName, Supplier<T> supplier);
    <T> T useRetryWithCustomConfig(RetryConfig config, String serviceName, Supplier<T> supplier, Supplier<T> fallbackSupplier);
    <T> T useCircuitBreakerAndRateLimiterWithCustomConfig(CircuitBreakerConfig circuitBreakerConfig, RateLimiterConfig rateLimiterConfig, String rateLimitKey, String serviceName, Supplier<T> supplier);
    <T> T useCircuitBreakerAndRateLimiterWithCustomConfig(CircuitBreakerConfig circuitBreakerConfig, RateLimiterConfig rateLimiterConfig, String rateLimitKey, String serviceName, Supplier<T> supplier, Supplier<T> fallbackSupplier);
}
