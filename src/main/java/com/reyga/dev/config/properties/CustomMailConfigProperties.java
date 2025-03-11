package com.reyga.dev.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.mail")
public class CustomMailConfigProperties {
    private String host;
    private int port;
    private String defaultSubject;
    private String defaultSender;
    private String defaultMessage;

    public CustomMailConfigProperties() {
    }

    public CustomMailConfigProperties(String host, int port, String defaultSubject, String defaultSender, String defaultMessage) {
        this.host = host;
        this.port = port;
        this.defaultSubject = defaultSubject;
        this.defaultSender = defaultSender;
        this.defaultMessage = defaultMessage;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDefaultSubject() {
        return defaultSubject;
    }

    public void setDefaultSubject(String defaultSubject) {
        this.defaultSubject = defaultSubject;
    }

    public String getDefaultSender() {
        return defaultSender;
    }

    public void setDefaultSender(String defaultSender) {
        this.defaultSender = defaultSender;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    @Override
    public String toString() {
        return "CustomMailConfigProperties{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", defaultSubject='" + defaultSubject + '\'' +
                ", defaultSender='" + defaultSender + '\'' +
                ", defaultMessage='" + defaultMessage + '\'' +
                '}';
    }
}
