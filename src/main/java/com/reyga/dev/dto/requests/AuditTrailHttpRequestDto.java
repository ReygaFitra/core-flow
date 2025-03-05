package com.reyga.dev.dto.requests;

public class AuditTrailHttpRequestDto {
    private String url;
    private String clientDate;
    private String ipAddress;
    private String latitude;
    private String longitude;
    private String clientInfo;
    private String httpMethod;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClientDate() {
        return clientDate;
    }

    public void setClientDate(String clientDate) {
        this.clientDate = clientDate;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    @Override
    public String toString() {
        return "AuditTrailHttpRequestDto{" +
                "url='" + url + '\'' +
                ", clientDate='" + clientDate + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", clientInfo='" + clientInfo + '\'' +
                ", httpMethod='" + httpMethod + '\'' +
                '}';
    }
}
