package com.reyga.dev.enumeration;

public enum ActivityKey {
    PROCESS_ID("processId"),
    SERVICE_NAME("service"),
    METHOD_NAME("methodName"),;

    private final String keyName;

    public String getKeyName() {
        return keyName;
    }

    ActivityKey(String keyName) {
        this.keyName = keyName;
    }
}
