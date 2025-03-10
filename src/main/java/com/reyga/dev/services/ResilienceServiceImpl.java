package com.reyga.dev.services;

import com.reyga.dev.utils.CommonLogger;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletionException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

@Service
public class ResilienceServiceImpl implements ResilienceService {

    private final CommonLogger logger;
    private final ConcurrentHashMap<String, RateLimiter> concurrentHashMapRateLimit = new ConcurrentHashMap<>();

    private final RateLimiterRegistry globalRateLimiterRegistry;
    private final CircuitBreakerRegistry globalCircuitBreakerRegistry;
    private final RetryRegistry globalRetryRegistry;

    public ResilienceServiceImpl(CommonLogger logger, RateLimiterConfig globalRateLimitConfig,
                                 RetryConfig globalRetryConfig, CircuitBreakerConfig globalCircuitBreakerConfig) {
        this.logger = logger;
        this.globalRateLimiterRegistry = RateLimiterRegistry.of(globalRateLimitConfig);
        this.globalCircuitBreakerRegistry = CircuitBreakerRegistry.of(globalCircuitBreakerConfig);
        this.globalRetryRegistry = RetryRegistry.of(globalRetryConfig);
    }


    @Override// RESILIENCE WITH GLOBAL CONFIGURATIONS
    public <T> T useRateLimitWithGlobalConfig(String rateLimitKey, String serviceName, Supplier<T> supplier) {
        RateLimiter rateLimiter = concurrentHashMapRateLimit.computeIfAbsent(rateLimitKey, id -> globalRateLimiterRegistry.rateLimiter(serviceName));

        Supplier<T> rateLimitedSupplied = RateLimiter.decorateSupplier(rateLimiter, supplier);
        return this.executeResilienceProcess(rateLimitedSupplied);
    }

    @Override
    public <T> T useRateLimitWithGlobalConfig(String rateLimitKey, String serviceName, Supplier<T> supplier, Supplier<T> fallbackSupplier) {
        RateLimiter rateLimiter = concurrentHashMapRateLimit.computeIfAbsent(rateLimitKey, id -> globalRateLimiterRegistry.rateLimiter(serviceName));

        Supplier<T> rateLimitedSupplied = RateLimiter.decorateSupplier(rateLimiter, supplier);
        return this.executeResilienceProcess(rateLimitedSupplied, fallbackSupplier);
    }

    @Override
    public <T> T useCircuitBreakerWithGlobalConfig(String serviceName, Supplier<T> supplier) {
        CircuitBreaker circuitBreaker = globalCircuitBreakerRegistry.circuitBreaker(serviceName);

        Supplier<T> circuitBreakerSupplied = CircuitBreaker.decorateSupplier(circuitBreaker, supplier);
        return this.executeResilienceProcess(circuitBreakerSupplied);
    }

    @Override
    public <T> T useCircuitBreakerWithGlobalConfig(String serviceName, Supplier<T> supplier, Supplier<T> fallbackSupplier) {
        CircuitBreaker circuitBreaker = globalCircuitBreakerRegistry.circuitBreaker(serviceName);

        Supplier<T> circuitBreakerSupplied = CircuitBreaker.decorateSupplier(circuitBreaker, supplier);
        return this.executeResilienceProcess(circuitBreakerSupplied, fallbackSupplier);
    }

    @Override
    public <T> T useRetryWithGlobalConfig(String serviceName, Supplier<T> supplier) {
        Retry retry = globalRetryRegistry.retry(serviceName);

        Supplier<T> retrySupplied = Retry.decorateSupplier(retry, supplier);
        return this.executeResilienceProcess(retrySupplied);
    }

    @Override
    public <T> T useRetryWithGlobalConfig(String serviceName, Supplier<T> supplier, Supplier<T> fallbackSupplier) {
        Retry retry = globalRetryRegistry.retry(serviceName);

        Supplier<T> retrySupplied = Retry.decorateSupplier(retry, supplier);
        return this.executeResilienceProcess(retrySupplied, fallbackSupplier);
    }

    @Override
    public <T> T useCircuitBreakerAndRateLimiterWithGlobalConfig(String rateLimitKey, String serviceName, Supplier<T> supplier) {
        CircuitBreaker circuitBreaker = globalCircuitBreakerRegistry.circuitBreaker(serviceName);
        RateLimiter rateLimiter = concurrentHashMapRateLimit.computeIfAbsent(rateLimitKey, id -> globalRateLimiterRegistry.rateLimiter(serviceName));

        Supplier<T> decoratedSupplied = CircuitBreaker.decorateSupplier(circuitBreaker,
                RateLimiter.decorateSupplier(rateLimiter, supplier));
        return this.executeResilienceProcess(decoratedSupplied);
    }

    @Override
    public <T> T useCircuitBreakerAndRateLimiterWithGlobalConfig(String rateLimitKey, String serviceName, Supplier<T> supplier, Supplier<T> fallbackSupplier) {
        CircuitBreaker circuitBreaker = globalCircuitBreakerRegistry.circuitBreaker(serviceName);
        RateLimiter rateLimiter = concurrentHashMapRateLimit.computeIfAbsent(rateLimitKey, id -> globalRateLimiterRegistry.rateLimiter(serviceName));

        Supplier<T> decoratedSupplied = CircuitBreaker.decorateSupplier(circuitBreaker,
                RateLimiter.decorateSupplier(rateLimiter, supplier));
        return this.executeResilienceProcess(decoratedSupplied, fallbackSupplier);
    }


    @Override// RESILIENCE WITH CUSTOM CONFIGURATIONS
    public <T> T useRateLimitWithCustomConfig(RateLimiterConfig config, String rateLimitKey, String serviceName, Supplier<T> supplier) {
        RateLimiterRegistry customRateLimiterRegistry = RateLimiterRegistry.of(config);
        RateLimiter rateLimiter = concurrentHashMapRateLimit.computeIfAbsent(rateLimitKey, id -> customRateLimiterRegistry.rateLimiter(serviceName));

        Supplier<T> rateLimitedSupplied = RateLimiter.decorateSupplier(rateLimiter, supplier);
        return this.executeResilienceProcess(rateLimitedSupplied);
    }

    @Override
    public <T> T useRateLimitWithCustomConfig(RateLimiterConfig config, String rateLimitKey, String serviceName, Supplier<T> supplier, Supplier<T> fallbackSupplier) {
        RateLimiterRegistry customRateLimiterRegistry = RateLimiterRegistry.of(config);
        RateLimiter rateLimiter = concurrentHashMapRateLimit.computeIfAbsent(rateLimitKey, id -> customRateLimiterRegistry.rateLimiter(serviceName));

        Supplier<T> rateLimitedSupplied = RateLimiter.decorateSupplier(rateLimiter, supplier);
        return this.executeResilienceProcess(rateLimitedSupplied, fallbackSupplier);
    }

    @Override
    public <T> T useCircuitBreakerWithCustomConfig(CircuitBreakerConfig config, String serviceName, Supplier<T> supplier) {
        CircuitBreakerRegistry customCircuitBreakerRegistry = CircuitBreakerRegistry.of(config);
        CircuitBreaker circuitBreaker = customCircuitBreakerRegistry.circuitBreaker(serviceName);

        Supplier<T> circuitBreakerSupplied = CircuitBreaker.decorateSupplier(circuitBreaker, supplier);
        return this.executeResilienceProcess(circuitBreakerSupplied);
    }

    @Override
    public <T> T useCircuitBreakerWithCustomConfig(CircuitBreakerConfig config, String serviceName, Supplier<T> supplier, Supplier<T> fallbackSupplier) {
        CircuitBreakerRegistry customCircuitBreakerRegistry = CircuitBreakerRegistry.of(config);
        CircuitBreaker circuitBreaker = customCircuitBreakerRegistry.circuitBreaker(serviceName);

        Supplier<T> circuitBreakerSupplied = CircuitBreaker.decorateSupplier(circuitBreaker, supplier);
        return this.executeResilienceProcess(circuitBreakerSupplied, fallbackSupplier);
    }

    @Override
    public <T> T useRetryWithCustomConfig(RetryConfig config, String serviceName, Supplier<T> supplier) {
        RetryRegistry customRetryRegistry = RetryRegistry.of(config);
        Retry retry = customRetryRegistry.retry(serviceName);

        Supplier<T> retrySupplied = Retry.decorateSupplier(retry, supplier);
        return this.executeResilienceProcess(retrySupplied);
    }

    @Override
    public <T> T useRetryWithCustomConfig(RetryConfig config, String serviceName, Supplier<T> supplier, Supplier<T> fallbackSupplier) {
        RetryRegistry customRetryRegistry = RetryRegistry.of(config);
        Retry retry = customRetryRegistry.retry(serviceName);

        Supplier<T> retrySupplied = Retry.decorateSupplier(retry, supplier);
        return this.executeResilienceProcess(retrySupplied, fallbackSupplier);
    }

    @Override
    public <T> T useCircuitBreakerAndRateLimiterWithCustomConfig(CircuitBreakerConfig circuitBreakerConfig, RateLimiterConfig rateLimiterConfig, String rateLimitKey, String serviceName, Supplier<T> supplier) {
        Supplier<T> decoratedSupplied = this.customRegistry(circuitBreakerConfig, rateLimiterConfig, rateLimitKey, serviceName, supplier);
        return this.executeResilienceProcess(decoratedSupplied);
    }

    @Override
    public <T> T useCircuitBreakerAndRateLimiterWithCustomConfig(CircuitBreakerConfig circuitBreakerConfig, RateLimiterConfig rateLimiterConfig, String rateLimitKey, String serviceName, Supplier<T> supplier, Supplier<T> fallbackSupplier) {
        Supplier<T> decoratedSupplied = this.customRegistry(circuitBreakerConfig, rateLimiterConfig, rateLimitKey, serviceName, supplier);
        return this.executeResilienceProcess(decoratedSupplied, fallbackSupplier);
    }

    private <T> T executeResilienceProcess(Supplier<T> decoratedSuppliedProcess) {
        try {
           return decoratedSuppliedProcess.get();
        } catch (CompletionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                logger.error("Execution failed: ", cause.getMessage());
                throw (RuntimeException) cause;
            }
            throw new RuntimeException("Unexpected execution error", e);
        }
    }

    private <T> T executeResilienceProcess(Supplier<T> decoratedSuppliedProcess, Supplier<T> fallbackSuppliedProcess) {
        try {
            return decoratedSuppliedProcess.get();
        } catch (Exception e) {
            logger.error("Execution failed: ", e.getMessage());
            return fallbackSuppliedProcess.get();
        }
    }

    private <T> Supplier<T> customRegistry(CircuitBreakerConfig circuitBreakerConfig, RateLimiterConfig rateLimiterConfig, String rateLimitKey, String serviceName, Supplier<T> supplier) {
        CircuitBreakerRegistry customCircuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);
        RateLimiterRegistry customRateLimiterRegistry = RateLimiterRegistry.of(rateLimiterConfig);

        CircuitBreaker circuitBreaker = customCircuitBreakerRegistry.circuitBreaker(serviceName);
        RateLimiter rateLimiter = concurrentHashMapRateLimit.computeIfAbsent(rateLimitKey, id -> customRateLimiterRegistry.rateLimiter(serviceName));

        return CircuitBreaker.decorateSupplier(circuitBreaker, RateLimiter.decorateSupplier(rateLimiter, supplier));
    }

}
