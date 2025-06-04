package com.example.BE.core.product.service.impl;

import com.example.BE.core.product.model.request.ProductRequest;
import com.example.BE.core.product.model.response.ProductResponse;
import com.example.BE.core.product.model.response.ProductsStatsResponse;
import com.example.BE.core.category.repository.CategoryRepository;
import com.example.BE.core.product.repository.ProductRepository;
import com.example.BE.core.product.service.ProductService;
import com.example.BE.entity.Category;
import com.example.BE.entity.Product;
import com.example.BE.infrastructure.common.ResponseObject;
import com.example.BE.infrastructure.constant.ProductStatus;
import com.example.BE.infrastructure.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseObject<ProductResponse> createProduct(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy danh mục"));

        Product product = new Product();
        mapRequestToProduct(request, product, category);

        Product savedProduct = productRepository.save(product);
        return new ResponseObject<>(true, HttpStatus.CREATED, convertToResponse(savedProduct), "Tạo sản phẩm thành công");
    }

    @Override
    public ResponseObject<ProductResponse> updateProduct(String id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy danh mục"));

        mapRequestToProduct(request, product, category);

        Product updatedProduct = productRepository.save(product);
        return new ResponseObject<>(true, HttpStatus.OK, convertToResponse(updatedProduct), "Cập nhật sản phẩm thành công");
    }

    @Override
    public ResponseObject<Void> deleteProduct(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm"));

        productRepository.delete(product);
        return new ResponseObject<>(true, HttpStatus.NO_CONTENT, null, "Xóa sản phẩm thành công");
    }

    @Override
    public ResponseObject<ProductResponse> getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm"));

        return new ResponseObject<>(true, HttpStatus.OK, convertToResponse(product), "Thành công");
    }

    @Override
    public ResponseObject<Page<ProductResponse>> getAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        Page<ProductResponse> responses = products.map(this::convertToResponse);
        return new ResponseObject<>(true, HttpStatus.OK, responses, "Thành công");
    }

    @Override
    public ResponseObject<Page<ProductResponse>> searchProducts(String keyword, Pageable pageable) {
        Page<Product> products = productRepository.searchProducts(keyword, pageable);
        Page<ProductResponse> responses = products.map(this::convertToResponse);
        return new ResponseObject<>(true, HttpStatus.OK, responses, "Thành công");
    }

    @Override
    public ResponseObject<Page<ProductResponse>> findByCategory(String categoryId, Pageable pageable) {
        Page<Product> products = productRepository.findByCategoryId(categoryId, pageable);
        Page<ProductResponse> responses = products.map(this::convertToResponse);
        return new ResponseObject<>(true, HttpStatus.OK, responses, "Thành công");
    }

    @Override
    public ResponseObject<Page<ProductResponse>> findByPriceRange(double minPrice, double maxPrice, Pageable pageable) {
        Page<Product> products = productRepository.findByPriceRange(minPrice, maxPrice, pageable);
        Page<ProductResponse> responses = products.map(this::convertToResponse);
        return new ResponseObject<>(true, HttpStatus.OK, responses, "Thành công");
    }

    @Override
    public ResponseObject<ProductsStatsResponse> getProductStats() {
        ProductsStatsResponse stats = new ProductsStatsResponse();
        stats.setTotalProducts(productRepository.count());
        stats.setAvailableProducts(productRepository.countByStatus(ProductStatus.SELLING));
        stats.setLowStockProducts(productRepository.findByQuantityLessThan(5).size());
        stats.setProductsByCategory(productRepository.countProductsByCategory());

        return new ResponseObject<>(true, HttpStatus.OK, stats, "Thành công");
    }

    private void mapRequestToProduct(ProductRequest request, Product product, Category category) {
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setBrand(request.getBrand());
        product.setCategory(category);

        if (request.getStatus() != null) {
            product.setStatus(request.getStatus());
        }
    }

    private ProductResponse convertToResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        response.setBrand(product.getBrand());
        response.setStatus(product.getStatus());
        response.setCategoryId(product.getCategory().getId());
        response.setCategoryName(product.getCategory().getName());
        response.setCreatedDate(product.getCreatedDate());
        response.setLastModifiedDate(product.getLastModifiedDate());
        return response;
    }
}