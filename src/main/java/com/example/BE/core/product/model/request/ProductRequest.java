package com.example.BE.core.product.model.request;

import com.example.BE.infrastructure.constant.EntityProperties;
import com.example.BE.infrastructure.constant.ProductStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    @NotBlank(message = "Tên sản phẩm không được để trống")
    @Size(max = EntityProperties.LENGTH_NAME, message = "Tên sản phẩm không vượt quá {max} ký tự")
    private String name;

    @Size(max = EntityProperties.LENGTH_DESCRIPTION, message = "Mô tả không vượt quá {max} ký tự")
    private String description;

    @Positive(message = "Giá bán phải là số dương")
    private double price;

    @Min(value = 0, message = "Số lượng không được âm")
    private int quantity;

    @Size(max = EntityProperties.LENGTH_BRAND, message = "Thương hiệu không vượt quá {max} ký tự")
    private String brand;

    @NotNull(message = "Danh mục không được để trống")
    private String categoryId;

    private ProductStatus status;
}
