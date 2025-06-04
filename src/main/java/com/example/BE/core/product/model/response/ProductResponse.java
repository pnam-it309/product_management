package com.example.BE.core.product.model.response;

import com.example.BE.infrastructure.constant.ProductStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String brand;
    private ProductStatus status;
    private String categoryId;
    private String categoryName;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
