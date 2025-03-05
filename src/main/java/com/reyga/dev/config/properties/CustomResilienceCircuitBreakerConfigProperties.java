package com.reyga.dev.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.resilience.circuitbreaker")
public class CustomResilienceCircuitBreakerConfigProperties {
    private float failureRateThreshold;
    private float slowCallRateThreshold;
    private long slowCallDurationThresholdSeconds;
    private int minimumNumberOfCalls;
    private int slidingWindowSize;
    private int permittedNumberOfCallsInHalfOpenState;
    private long waitDurationInOpenStateSeconds;
    private boolean automaticTransitionFromOpenToHalfOpenEnabled;

    public CustomResilienceCircuitBreakerConfigProperties() {
    }

    public CustomResilienceCircuitBreakerConfigProperties(float failureRateThreshold, float slowCallRateThreshold, long slowCallDurationThresholdSeconds, int minimumNumberOfCalls, int slidingWindowSize, int permittedNumberOfCallsInHalfOpenState, long waitDurationInOpenStateSeconds, boolean automaticTransitionFromOpenToHalfOpenEnabled) {
        this.failureRateThreshold = failureRateThreshold;
        this.slowCallRateThreshold = slowCallRateThreshold;
        this.slowCallDurationThresholdSeconds = slowCallDurationThresholdSeconds;
        this.minimumNumberOfCalls = minimumNumberOfCalls;
        this.slidingWindowSize = slidingWindowSize;
        this.permittedNumberOfCallsInHalfOpenState = permittedNumberOfCallsInHalfOpenState;
        this.waitDurationInOpenStateSeconds = waitDurationInOpenStateSeconds;
        this.automaticTransitionFromOpenToHalfOpenEnabled = automaticTransitionFromOpenToHalfOpenEnabled;
    }

    public float getFailureRateThreshold() {
        return failureRateThreshold;
    }

    public void setFailureRateThreshold(float failureRateThreshold) {
        this.failureRateThreshold = failureRateThreshold;
    }

    public float getSlowCallRateThreshold() {
        return slowCallRateThreshold;
    }

    public void setSlowCallRateThreshold(float slowCallRateThreshold) {
        this.slowCallRateThreshold = slowCallRateThreshold;
    }

    public long getSlowCallDurationThresholdSeconds() {
        return slowCallDurationThresholdSeconds;
    }

    public void setSlowCallDurationThresholdSeconds(long slowCallDurationThresholdSeconds) {
        this.slowCallDurationThresholdSeconds = slowCallDurationThresholdSeconds;
    }

    public int getMinimumNumberOfCalls() {
        return minimumNumberOfCalls;
    }

    public void setMinimumNumberOfCalls(int minimumNumberOfCalls) {
        this.minimumNumberOfCalls = minimumNumberOfCalls;
    }

    public int getSlidingWindowSize() {
        return slidingWindowSize;
    }

    public void setSlidingWindowSize(int slidingWindowSize) {
        this.slidingWindowSize = slidingWindowSize;
    }

    public int getPermittedNumberOfCallsInHalfOpenState() {
        return permittedNumberOfCallsInHalfOpenState;
    }

    public void setPermittedNumberOfCallsInHalfOpenState(int permittedNumberOfCallsInHalfOpenState) {
        this.permittedNumberOfCallsInHalfOpenState = permittedNumberOfCallsInHalfOpenState;
    }

    public long getWaitDurationInOpenStateSeconds() {
        return waitDurationInOpenStateSeconds;
    }

    public void setWaitDurationInOpenStateSeconds(long waitDurationInOpenStateSeconds) {
        this.waitDurationInOpenStateSeconds = waitDurationInOpenStateSeconds;
    }

    public boolean isAutomaticTransitionFromOpenToHalfOpenEnabled() {
        return automaticTransitionFromOpenToHalfOpenEnabled;
    }

    public void setAutomaticTransitionFromOpenToHalfOpenEnabled(boolean automaticTransitionFromOpenToHalfOpenEnabled) {
        this.automaticTransitionFromOpenToHalfOpenEnabled = automaticTransitionFromOpenToHalfOpenEnabled;
    }

    @Override
    public String toString() {
        return "CustomResilienceCircuitBreakerConfigProperties{" +
                "failureRateThreshold=" + failureRateThreshold +
                ", slowCallRateThreshold=" + slowCallRateThreshold +
                ", slowCallDurationThresholdSeconds=" + slowCallDurationThresholdSeconds +
                ", minimumNumberOfCalls=" + minimumNumberOfCalls +
                ", slidingWindowSize=" + slidingWindowSize +
                ", permittedNumberOfCallsInHalfOpenState=" + permittedNumberOfCallsInHalfOpenState +
                ", waitDurationInOpenStateSeconds=" + waitDurationInOpenStateSeconds +
                ", automaticTransitionFromOpenToHalfOpenEnabled=" + automaticTransitionFromOpenToHalfOpenEnabled +
                '}';
    }
}
