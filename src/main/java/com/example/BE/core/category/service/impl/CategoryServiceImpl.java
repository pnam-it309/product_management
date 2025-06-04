package com.example.BE.core.category.service.impl;

import com.example.BE.core.category.model.request.CategoryRequest;
import com.example.BE.core.category.model.response.CategoryResponse;
import com.example.BE.core.category.repository.CategoryRepository;
import com.example.BE.core.category.service.CategoryService;
import com.example.BE.core.product.repository.ProductRepository;
import com.example.BE.entity.Category;
import com.example.BE.infrastructure.common.ResponseObject;
import com.example.BE.infrastructure.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public ResponseObject<CategoryResponse> createCategory(CategoryRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            return new ResponseObject<>(false, HttpStatus.BAD_REQUEST, null, "Tên danh mục đã tồn tại");
        }

        Category category = new Category();
        category.setName(request.getName());

        Category savedCategory = categoryRepository.save(category);
        return new ResponseObject<>(true, HttpStatus.CREATED, convertToResponse(savedCategory), "Tạo danh mục thành công");
    }

    @Override
    public ResponseObject<CategoryResponse> updateCategory(String id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy danh mục"));

        if (!category.getName().equals(request.getName()) &&
                categoryRepository.existsByName(request.getName())) {
            return new ResponseObject<>(false, HttpStatus.BAD_REQUEST, null, "Tên danh mục đã tồn tại");
        }

        category.setName(request.getName());
        Category updatedCategory = categoryRepository.save(category);
        return new ResponseObject<>(true, HttpStatus.OK, convertToResponse(updatedCategory), "Cập nhật danh mục thành công");
    }

    @Override
    public ResponseObject<Void> deleteCategory(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy danh mục"));

        if (productRepository.existsByCategoryId(id)) {
            return new ResponseObject<>(false, HttpStatus.BAD_REQUEST, null, "Không thể xóa danh mục có sản phẩm");
        }

        categoryRepository.delete(category);
        return new ResponseObject<>(true, HttpStatus.NO_CONTENT, null, "Xóa danh mục thành công");
    }

    @Override
    public ResponseObject<CategoryResponse> getCategoryById(String id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy danh mục"));

        return new ResponseObject<>(true, HttpStatus.OK, convertToResponse(category), "Thành công");
    }

    @Override
    public ResponseObject<Page<CategoryResponse>> getAllCategories(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        Page<CategoryResponse> responses = categories.map(this::convertToResponse);
        return new ResponseObject<>(true, HttpStatus.OK, responses, "Thành công");
    }

    private CategoryResponse convertToResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setCreatedDate(category.getCreatedDate());
        response.setLastModifiedDate(category.getLastModifiedDate());
        return response;
    }
}