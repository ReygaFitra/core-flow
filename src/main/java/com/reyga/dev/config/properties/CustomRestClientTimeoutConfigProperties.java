package com.reyga.dev.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.rest-client.timeout")
public class CustomRestClientTimeoutConfigProperties {
    private int connectReadTimeout;
    private int connectTimeout;

    public CustomRestClientTimeoutConfigProperties() {
    }

    public CustomRestClientTimeoutConfigProperties(int connectReadTimeout, int connectTimeout) {
        this.connectReadTimeout = connectReadTimeout;
        this.connectTimeout = connectTimeout;
    }

    public int getConnectReadTimeout() {
        return connectReadTimeout;
    }

    public void setConnectReadTimeout(int connectReadTimeout) {
        this.connectReadTimeout = connectReadTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    @Override
    public String toString() {
        return "CustomRestClientTimeoutConfigProperties{" +
                "connectReadTimeout=" + connectReadTimeout +
                ", connectTimeout=" + connectTimeout +
                '}';
    }
}
