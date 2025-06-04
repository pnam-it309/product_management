package com.example.BE.core.product.service;

import com.example.BE.core.product.model.request.ProductRequest;
import com.example.BE.core.product.model.response.ProductResponse;
import com.example.BE.core.product.model.response.ProductsStatsResponse;
import com.example.BE.infrastructure.common.ResponseObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {
    ResponseObject<ProductResponse> createProduct(ProductRequest request);
    ResponseObject<ProductResponse> updateProduct(String id, ProductRequest request);
    ResponseObject<Void> deleteProduct(String id);
    ResponseObject<ProductResponse> getProductById(String id);
    ResponseObject<Page<ProductResponse>> getAllProducts(Pageable pageable);
    ResponseObject<Page<ProductResponse>> searchProducts(String keyword, Pageable pageable);
    ResponseObject<Page<ProductResponse>> findByCategory(String categoryId, Pageable pageable);
    ResponseObject<Page<ProductResponse>> findByPriceRange(double minPrice, double maxPrice, Pageable pageable);
    ResponseObject<ProductsStatsResponse> getProductStats();

}