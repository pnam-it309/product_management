package com.example.BE.core.category.service;

import com.example.BE.core.category.model.request.CategoryRequest;
import com.example.BE.core.category.model.response.CategoryResponse;
import com.example.BE.infrastructure.common.ResponseObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CategoryService {
    ResponseObject<CategoryResponse> createCategory(CategoryRequest request);
    ResponseObject<CategoryResponse> updateCategory(String id, CategoryRequest request);
    ResponseObject<Void> deleteCategory(String id);
    ResponseObject<CategoryResponse> getCategoryById(String id);
    ResponseObject<Page<CategoryResponse>> getAllCategories(Pageable pageable);
}