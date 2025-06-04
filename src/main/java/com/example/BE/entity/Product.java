package com.example.BE.entity;


import com.example.BE.entity.base.PrimaryEntity;
import com.example.BE.infrastructure.constant.EntityProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product extends PrimaryEntity implements Serializable {
    @Column(length = EntityProperties.LENGTH_NAME, nullable = false)
    private String name;

    @Column(length = EntityProperties.LENGTH_DESCRIPTION)
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int quantity;

    @Column(length = EntityProperties.LENGTH_BRAND)
    private String brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
