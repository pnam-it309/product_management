package com.example.BE.core.category.model.request;

import com.example.BE.infrastructure.constant.EntityProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {
    @NotBlank(message = "Tên danh mục không được để trống")
    @Size(max = EntityProperties.LENGTH_NAME, message = "Tên danh mục không vượt quá {max} ký tự")
    private String name;
}
