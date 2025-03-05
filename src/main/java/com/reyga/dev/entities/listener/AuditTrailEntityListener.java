package com.reyga.dev.entities.listener;

import com.reyga.dev.entities.AuditTrailEntity;
import com.reyga.dev.utils.DateUtil;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.UUID;

public class AuditTrailEntityListener {
    @PrePersist
    protected void onPrePersist(AuditTrailEntity entity) {
        if (entity.getId() == null)
            entity.setId(UUID.randomUUID().toString());

        if (entity.getCreatedDate() == null)
            entity.setCreatedDate(DateUtil.nowSystemTimezone());
    }

    @PreUpdate
    protected void onPreUpdate(AuditTrailEntity entity) {
    }
}
