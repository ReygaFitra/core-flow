package com.reyga.dev.enumeration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public enum ActivityType {
    INSTANCE;

    private final Map<String, Object> activityLog = new LinkedHashMap<>();
    private final List<Map<String, String>> processList = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final static String PROCESS = "process";

    ActivityType() {
        activityLog.put(PROCESS, new ArrayList<>());
    }

    public void addRequestAndService(String requestId, String serviceName) {
        activityLog.clear();
        activityLog.put(ActivityKey.REQUEST_ID.getKeyName(), requestId);
        activityLog.put(ActivityKey.SERVICE_NAME.getKeyName(), serviceName);
        activityLog.put(PROCESS, processList);
    }

    public void addProcess(String processId, String processName) {
        Map<String, String> processEntry = new LinkedHashMap<>();
        processEntry.put(ActivityKey.PROCESS_ID.getKeyName(), processId);
        processEntry.put(ActivityKey.PROCESS_NAME.getKeyName(), processName);
        processList.add(processEntry);
    }

    public void addNewProcess(Map<String, String> processEntry) {
        processList.add(processEntry);
    }

    public String getActivityLog() {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(activityLog);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getActivityKey(Object value) {
        return activityLog.keySet().stream().filter(key -> key.equals(value)).findFirst().orElse(null);
    }

    public Object getActivityValue(String key) {
        return activityLog.get(key);
    }
}

