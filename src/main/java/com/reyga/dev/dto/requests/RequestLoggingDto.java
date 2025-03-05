package com.reyga.dev.dto.requests;

import java.util.Map;

public class RequestLoggingDto {
    private Map<String, Object> requestParams;
    private Map<String, Object> requestMultiPart;
    private Map<String, Object> requestPathVariable;
    private Object requestBody;

    public RequestLoggingDto() {
    }

    public RequestLoggingDto(Map<String, Object> requestParams, Map<String, Object> requestMultiPart, Map<String, Object> requestPathVariable, Object requestBody) {
        this.requestParams = requestParams;
        this.requestMultiPart = requestMultiPart;
        this.requestPathVariable = requestPathVariable;
        this.requestBody = requestBody;
    }

    public Map<String, Object> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(Map<String, Object> requestParams) {
        this.requestParams = requestParams;
    }

    public Map<String, Object> getRequestMultiPart() {
        return requestMultiPart;
    }

    public void setRequestMultiPart(Map<String, Object> requestMultiPart) {
        this.requestMultiPart = requestMultiPart;
    }

    public Map<String, Object> getRequestPathVariable() {
        return requestPathVariable;
    }

    public void setRequestPathVariable(Map<String, Object> requestPathVariable) {
        this.requestPathVariable = requestPathVariable;
    }

    public Object getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(Object requestBody) {
        this.requestBody = requestBody;
    }

    @Override
    public String toString() {
        return "{" +
                "requestParams=" + requestParams +
                ", requestMultiPart=" + requestMultiPart +
                ", requestPathVariable=" + requestPathVariable +
                ", requestBody=" + requestBody +
                '}';
    }
}
