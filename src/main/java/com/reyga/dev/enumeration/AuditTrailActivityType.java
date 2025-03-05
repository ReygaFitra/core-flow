package com.reyga.dev.enumeration;

public enum AuditTrailActivityType {
    LOGIN("1", AuditType.GENERAL), LOGOUT("2", AuditType.GENERAL), CHANNEL("3", AuditType.CHANNEL), ADD("4", AuditType.APPROVAL),
    EDIT("5", AuditType.APPROVAL), DELETE("6", AuditType.APPROVAL), FORGOT_PASSWORD("7", AuditType.GENERAL), UNLOCK_USER("8", AuditType.APPROVAL),
    CHANGE_PASSWORD("9", AuditType.GENERAL), APPROVE_PT("10", AuditType.PENDING_TASK), REJECT_PT("11", AuditType.PENDING_TASK),
    RELEASE_SESSION("12", AuditType.GENERAL), CHANNEL_APPROVAL("13", AuditType.APPROVAL), RESET_PASSWORD("14", AuditType.GENERAL),
    DASHBOARD_WIDGET("15", AuditType.GENERAL), REQUEST_OTP("16", AuditType.GENERAL);

    public static final String LOGIN_TYPE = "LOGIN";
    public static final String LOGOUT_TYPE = "LOGOUT";
    public static final String CHANNEL_TYPE = "CHANNEL";
    public static final String ADD_TYPE = "ADD";
    public static final String EDIT_TYPE = "EDIT";
    public static final String DELETE_TYPE = "DELETE";
    public static final String FORGOT_PASSWORD_TYPE = "FORGOT_PASSWORD";
    public static final String UNLOCK_USER_TYPE = "UNLOCK_USER";
    public static final String CHANGE_PASSWORD_TYPE = "CHANGE_PASSWORD";
    public static final String APPROVE_PT_TYPE = "APPROVE_PT";
    public static final String REJECT_PT_TYPE = "REJECT_PT";
    public static final String RELEASE_SESSION_TYPE = "RELEASE_SESSION";
    public static final String CHANNEL_APPROVAL_TYPE = "CHANNEL_APPROVAL";
    public static final String RESET_PASSWORD_TYPE = "RESET_PASSWORD";
    public static final String DASHBOARD_WIDGET_TYPE = "DASHBOARD_WIDGET";
    public static final String REQUEST_OTP_TYPE = "REQUEST_OTP";

    String activityTypeCode;
    AuditType auditType;

    AuditTrailActivityType(String activityTypeCode, AuditType auditType) {
        this.activityTypeCode = activityTypeCode;
        this.auditType = auditType;
    }

    public String getActivityTypeCode() {
        return activityTypeCode;
    }

    public AuditType getAuditType() {
        return auditType;
    }

    public static AuditTrailActivityType getByActivityTypeCode(String activityTypeCode) {
        for (AuditTrailActivityType typeEnum : AuditTrailActivityType.values()) {
            if (typeEnum.getActivityTypeCode().equals(activityTypeCode))
                return typeEnum;
        }
        return null;
    }
}
