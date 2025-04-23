package com.reyga.dev.dto.content.base;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseContentDto {

    private Map<String, Object> activityLog = new LinkedHashMap<>();
    private List<Map<String, String>> processList = new ArrayList<>();

    public Map<String, Object> getActivityLog() {
        if (activityLog == null) {
            activityLog = new LinkedHashMap<>();
        }
        return activityLog;
    }

    public void setActivityLog(Map<String, Object> activityLog) {
        this.activityLog = activityLog;
    }

    public List<Map<String, String>> getProcessList() {
        if (processList == null) {
            processList = new ArrayList<>();
        }
        return processList;
    }

    public void setProcessList(List<Map<String, String>> processList) {
        this.processList = processList;
    }
}
