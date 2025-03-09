package com.reyga.dev.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.web-client")
public class CustomWebClientConfigProperties {
    private int connectTimeout;
    private int responseTimeout;
    private int readTimeout;
    private int writeTimeout;

    public CustomWebClientConfigProperties() {
    }

    public CustomWebClientConfigProperties(int connectTimeout, int responseTimeout, int readTimeout, int writeTimeout) {
        this.connectTimeout = connectTimeout;
        this.responseTimeout = responseTimeout;
        this.readTimeout = readTimeout;
        this.writeTimeout = writeTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getResponseTimeout() {
        return responseTimeout;
    }

    public void setResponseTimeout(int responseTimeout) {
        this.responseTimeout = responseTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public int getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(int writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    @Override
    public String toString() {
        return "CustomWebClientConfigProperties{" +
                "connectTimeout=" + connectTimeout +
                ", responseTimeout=" + responseTimeout +
                ", readTimeout=" + readTimeout +
                ", writeTimeout=" + writeTimeout +
                '}';
    }
}
