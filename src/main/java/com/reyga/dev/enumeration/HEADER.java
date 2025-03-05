package com.reyga.dev.enumeration;

public enum HEADER {
    ACCESS_TOKEN("x-access-token"),
    USERNAME("x-user-name"),
    REQUEST_ID("x-request-id"),
    METHOD("x-request-method"),
    STATUS_CODE("x-status-code"),
    REQUEST_ENDPOINT("x-request-uri"),
    FORWARDED_FOR("x-forwarded-for"),
    PACKAGE_INFO("x-package-name"),
    EXCEPTION("x-request-exception"),
    REQUEST("x-request"),
    RESPONSE("x-response"),
    USER_AGENT("user-agent"),
    RESPONSE_TIME("x-response-time");

    public String getValue() {
        return value;
    }

    private final String value;

    HEADER(String value) {
        this.value = value;
    }
}
