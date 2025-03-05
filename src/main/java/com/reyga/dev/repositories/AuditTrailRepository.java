package com.reyga.dev.repositories;

import com.reyga.dev.entities.AuditTrailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditTrailRepository extends JpaRepository<AuditTrailEntity, String> {
}
