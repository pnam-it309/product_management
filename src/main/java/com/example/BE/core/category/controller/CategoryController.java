package com.example.BE.core.category.controller;

import com.example.BE.core.category.model.request.CategoryRequest;
import com.example.BE.core.category.model.response.CategoryResponse;
import com.example.BE.core.category.service.CategoryService;
import com.example.BE.infrastructure.common.ResponseObject;
import com.example.BE.infrastructure.constant.router.RoutCategory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(RoutCategory.CATEGORY_BASE_URL)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ResponseObject<CategoryResponse>> createCategory(
            @Valid @RequestBody CategoryRequest request) {
        ResponseObject<CategoryResponse> response = categoryService.createCategory(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject<CategoryResponse>> updateCategory(
            @PathVariable String id,
            @Valid @RequestBody CategoryRequest request) {
        ResponseObject<CategoryResponse> response = categoryService.updateCategory(id, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject<Void>> deleteCategory(@PathVariable String id) {
        ResponseObject<Void> response = categoryService.deleteCategory(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject<CategoryResponse>> getCategoryById(@PathVariable String id) {
        ResponseObject<CategoryResponse> response = categoryService.getCategoryById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseObject<Page<CategoryResponse>>> getAllCategories(Pageable pageable) {
        ResponseObject<Page<CategoryResponse>> response = categoryService.getAllCategories(pageable);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}