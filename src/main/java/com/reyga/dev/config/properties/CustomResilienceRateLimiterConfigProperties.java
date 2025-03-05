package com.reyga.dev.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.resilience.ratelimiter")
public class CustomResilienceRateLimiterConfigProperties {
    private int limit;
    private long refresh;
    private long timeout;

    public CustomResilienceRateLimiterConfigProperties() {
    }

    public CustomResilienceRateLimiterConfigProperties(int limit, long refresh, long timeout) {
        this.limit = limit;
        this.refresh = refresh;
        this.timeout = timeout;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getRefresh() {
        return refresh;
    }

    public void setRefresh(long refresh) {
        this.refresh = refresh;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        return "CustomResilienceConfigProperties{" +
                "limit='" + limit + '\'' +
                ", refresh='" + refresh + '\'' +
                ", timeout='" + timeout + '\'' +
                '}';
    }
}
