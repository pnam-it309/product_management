package com.example.BE.entity.base;

import com.example.BE.infrastructure.constant.EntityProperties;
import com.example.BE.infrastructure.constant.ProductStatus;
import com.example.BE.infrastructure.listener.CreatePrimaryEntityListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(CreatePrimaryEntityListener.class)
public abstract class PrimaryEntity extends AuditEntity implements IsIdentified {
    @Id
    @Column(length = EntityProperties.LENGTH_ID, updatable = false)
    private String id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
}
