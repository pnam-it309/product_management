package com.example.BE.core.product.controller;

import com.example.BE.core.product.model.request.ProductRequest;
import com.example.BE.core.product.model.response.ProductResponse;
import com.example.BE.core.product.model.response.ProductsStatsResponse;
import com.example.BE.core.product.service.ProductService;
import com.example.BE.infrastructure.common.PageableObject;
import com.example.BE.infrastructure.common.PageableRequest;
import com.example.BE.infrastructure.common.ResponseObject;
import com.example.BE.infrastructure.constant.router.RoutProduct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(RoutProduct.PRODUCT_BASE_URL)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping
    public ResponseEntity<ResponseObject<ProductResponse>> createProduct(
            @Valid @RequestBody ProductRequest request) {
        ResponseObject<ProductResponse> response = productService.createProduct(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject<ProductResponse>> updateProduct(
            @PathVariable String id,
            @Valid @RequestBody ProductRequest request) {
        ResponseObject<ProductResponse> response = productService.updateProduct(id, request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject<Void>> deleteProduct(@PathVariable String id) {
        ResponseObject<Void> response = productService.deleteProduct(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject<ProductResponse>> getProductById(@PathVariable String id) {
        ResponseObject<ProductResponse> response = productService.getProductById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseObject<Page<ProductResponse>>> getAllProducts(Pageable pageable) {
        ResponseObject<Page<ProductResponse>> response = productService.getAllProducts(pageable);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseObject<Page<ProductResponse>>> searchProducts(
            @RequestParam String keyword,
            Pageable pageable) {
        ResponseObject<Page<ProductResponse>> response = productService.searchProducts(keyword, pageable);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ResponseObject<Page<ProductResponse>>> getProductsByCategory(
            @PathVariable String categoryId,
            Pageable pageable) {
        ResponseObject<Page<ProductResponse>> response = productService.findByCategory(categoryId, pageable);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/price-range")
    public ResponseEntity<ResponseObject<Page<ProductResponse>>> getProductsByPriceRange(
            @RequestParam double minPrice,
            @RequestParam double maxPrice,
            Pageable pageable) {
        ResponseObject<Page<ProductResponse>> response = productService.findByPriceRange(minPrice, maxPrice, pageable);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/stats")
    public ResponseEntity<ResponseObject<ProductsStatsResponse>> getProductStats() {
        ResponseObject<ProductsStatsResponse> response = productService.getProductStats();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}