package com.example.BE.core.category.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryResponse {
    private String id;
    private String name;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}