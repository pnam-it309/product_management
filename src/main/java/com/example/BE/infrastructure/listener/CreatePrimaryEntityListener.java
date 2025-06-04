package com.example.BE.infrastructure.listener;

import com.example.BE.entity.base.PrimaryEntity;
import com.example.BE.infrastructure.constant.ProductStatus;
import jakarta.persistence.PrePersist;

import java.util.UUID;

public class CreatePrimaryEntityListener {

    @PrePersist
    public void onCreate(PrimaryEntity entity) {
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID().toString());
        }
        if (entity.getStatus() == null) {
            entity.setStatus(ProductStatus.SELLING);
        }
    }
}
