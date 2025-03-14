package com.reyga.dev.enumeration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public enum ActivityType {
    INSTANCE;

    private final Map<String, Object> activities = new HashMap<>();

    public Map<String, Object> getActivities() {
        return activities;
    }

    public void addActivities(String key, Object value) {
        activities.put(key, value);
    }

    public Map<String, Object> getAllActivities() {
        return new LinkedHashMap<>(activities);
    }

    public String getActivityKey(Object value) {
        return activities.keySet().stream().filter(key -> key.equals(value)).findFirst().orElse(null);
    }

    public Object getActivityValue(String key) {
        return activities.get(key);
    }

}
