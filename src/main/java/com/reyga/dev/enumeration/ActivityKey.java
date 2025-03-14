package com.reyga.dev.enumeration;

public enum ActivityKey {
    REQUEST_ID("requestId"),
    PROCESS_ID("processId"),
    SERVICE_NAME("service"),
    PROCESS_NAME("processName");

    private final String keyName;

    public String getKeyName() {
        return keyName;
    }

    ActivityKey(String keyName) {
        this.keyName = keyName;
    }
}
