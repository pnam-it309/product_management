package com.example.BE.infrastructure.listener;

import com.example.BE.entity.base.AuditEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class AuditEntityListener {
    @PrePersist
    public void onCreate(AuditEntity entity) {
        entity.setCreatedDate(LocalDateTime.now());
        entity.setLastModifiedDate(LocalDateTime.now());
    }

    @PreUpdate
    public void onUpdate(AuditEntity entity) {
        entity.setLastModifiedDate(LocalDateTime.now());
    }
}
