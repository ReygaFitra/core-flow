package com.reyga.dev.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reyga.dev.enumeration.ActivityKey;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ActivityConstruction {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final static String PROCESS = "process";

    public void initProcess(Map<String, Object> activityLog, List<Map<String, String>> processList, String requestId, String serviceName) {
        activityLog.clear();
        activityLog.put(ActivityKey.REQUEST_ID.getKeyName(), requestId);
        activityLog.put(ActivityKey.SERVICE_NAME.getKeyName(), serviceName);
        activityLog.put(PROCESS, processList);
    }

    public void addNewProcess(List<Map<String, String>> processList, String processId, String processName) {
        Map<String, String> processEntry = new LinkedHashMap<>();
        processEntry.put(ActivityKey.PROCESS_ID.getKeyName(), processId);
        processEntry.put(ActivityKey.PROCESS_NAME.getKeyName(), processName);
        processList.add(processEntry);
    }

    public String getActivityLog(boolean withPrettier, Map<String, Object> activityLog) {
        try {
            if (withPrettier) {
                return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(activityLog);
            }
            return objectMapper.writeValueAsString(activityLog);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getActivityKey(Map<String, Object> activityLog, Object value) {
        return activityLog.keySet().stream().filter(key -> key.equals(value)).findFirst().orElse(null);
    }

    public Object getActivityValue(Map<String, Object> activityLog, String key) {
        return activityLog.get(key);
    }
}
