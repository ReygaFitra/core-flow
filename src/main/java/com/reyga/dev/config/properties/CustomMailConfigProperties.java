package com.reyga.dev.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.mail")
public class CustomMailConfigProperties {
    private String host;
    private int port;
    private String username;
    private String password;
    private String connectTimeout;
    private String timeout;
    private String writeTimeout;
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

    public String getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(String connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getWriteTimeout() {
        return writeTimeout;
    }

    public void setWriteTimeout(String writeTimeout) {
        this.writeTimeout = writeTimeout;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CustomMailConfigProperties{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", connectTimeout='" + connectTimeout + '\'' +
                ", timeout='" + timeout + '\'' +
                ", writeTimeout='" + writeTimeout + '\'' +
                ", defaultSubject='" + defaultSubject + '\'' +
                ", defaultSender='" + defaultSender + '\'' +
                ", defaultMessage='" + defaultMessage + '\'' +
                '}';
    }
}
