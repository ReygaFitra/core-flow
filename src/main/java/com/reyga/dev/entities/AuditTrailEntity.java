package com.reyga.dev.entities;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "AUDIT_TRAIL")
@EntityListeners(AuditingEntityListener.class)
public class AuditTrailEntity {
    @Id
    @Column(name = "ID", length = 50)
    private String id;

    @Column(name = "CREATED_DATE", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "CLIENT_DATE", nullable = false, length = 100)
    private String clientDate;

    @Column(name = "IP_ADDRESS", nullable = false, length = 20)
    private String ipAddress;

    @Column(name = "LATITUDE", length = 20)
    private String latitude;

    @Column(name = "LONGITUDE", length = 20)
    private String longitude;

    @Column(name = "CLIENT_INFO", nullable = false, length = 200)
    private String clientInfo;

    @Column(name = "IS_SUCCESS", nullable = false)
    private boolean isSuccess;

    @Column(name = "STATUS_CODE", nullable = false, length = 20)
    private String statusCode;

    @Column(name = "ACTIVITY_TYPE", nullable = false, length = 20)
    private String activityType;

    @Column(name = "ACTIVITY_DESCRIPTION", nullable = false, length = 100)
    private String activityDescription;

    @Column(name = "USER_ID", length = 50)
    private String userId;

    @Lob
    @Column(name = "REQUEST", nullable = false)
    private String request;

    @Lob
    @Column(name = "RESPONSE")
    private String response;

    @Column(name = "MENU_CODE", length = 50)
    private String menuCode;

    public AuditTrailEntity() {
    }

    public AuditTrailEntity(String id, LocalDateTime createdDate, String clientDate, String ipAddress, String latitude, String longitude, String clientInfo, boolean isSuccess, String statusCode, String activityType, String activityDescription, String userId, String request, String response, String menuCode) {
        this.id = id;
        this.createdDate = createdDate;
        this.clientDate = clientDate;
        this.ipAddress = ipAddress;
        this.latitude = latitude;
        this.longitude = longitude;
        this.clientInfo = clientInfo;
        this.isSuccess = isSuccess;
        this.statusCode = statusCode;
        this.activityType = activityType;
        this.activityDescription = activityDescription;
        this.userId = userId;
        this.request = request;
        this.response = response;
        this.menuCode = menuCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
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

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    @Override
    public String toString() {
        return "AuditTrailEntity{" +
                "id='" + id + '\'' +
                ", createdDate=" + createdDate +
                ", clientDate='" + clientDate + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", clientInfo='" + clientInfo + '\'' +
                ", isSuccess=" + isSuccess +
                ", statusCode='" + statusCode + '\'' +
                ", activityType='" + activityType + '\'' +
                ", activityDescription='" + activityDescription + '\'' +
                ", userId='" + userId + '\'' +
                ", request='" + request + '\'' +
                ", response='" + response + '\'' +
                ", menuCode='" + menuCode + '\'' +
                '}';
    }
}
