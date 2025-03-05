package com.reyga.dev.enumeration;

public enum AuditType {
    GENERAL(false, false), CHANNEL(true, false),
    APPROVAL(false, true), PENDING_TASK(false, true);

    boolean needAdditionalDataBeforeProcess;
    boolean needAdditionalDataAfterProcess;

    AuditType(boolean needAdditionalDataBeforeProcess, boolean needAdditionalDataAfterProcess) {
        this.needAdditionalDataBeforeProcess = needAdditionalDataBeforeProcess;
        this.needAdditionalDataAfterProcess = needAdditionalDataAfterProcess;
    }

    public boolean isNeedAdditionalDataBeforeProcess() {
        return needAdditionalDataBeforeProcess;
    }

    public boolean isNeedAdditionalDataAfterProcess() {
        return needAdditionalDataAfterProcess;
    }
}
