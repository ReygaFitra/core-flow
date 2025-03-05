package com.reyga.dev.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.resilience.retry")
public class CustomResilienceRetryConfigProperties {
    private int maxAttempts;
    private long waitDurationSeconds;
    private Class<? extends Throwable> retryExceptionClass;

    public CustomResilienceRetryConfigProperties() {
    }

    public CustomResilienceRetryConfigProperties(int maxAttempts, long waitDurationSeconds, Class<? extends Throwable> retryExceptionClass) {
        this.maxAttempts = maxAttempts;
        this.waitDurationSeconds = waitDurationSeconds;
        this.retryExceptionClass = retryExceptionClass;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public long getWaitDurationSeconds() {
        return waitDurationSeconds;
    }

    public void setWaitDurationSeconds(long waitDurationSeconds) {
        this.waitDurationSeconds = waitDurationSeconds;
    }

    public Class<? extends Throwable> getRetryExceptionClass() {
        return retryExceptionClass;
    }

    public void setRetryExceptionClass(Class<? extends Throwable> retryExceptionClass) {
        this.retryExceptionClass = retryExceptionClass;
    }

    @Override
    public String toString() {
        return "CustomResilienceRetryConfigProperties{" +
                "maxAttempts=" + maxAttempts +
                ", waitDurationSeconds=" + waitDurationSeconds +
                ", retryExceptionClass=" + retryExceptionClass +
                '}';
    }
}
