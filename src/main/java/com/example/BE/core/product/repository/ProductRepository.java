package com.example.BE.core.product.repository;

import com.example.BE.entity.Product;
import com.example.BE.infrastructure.constant.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Page<Product> findByCategoryId(String categoryId, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Product> searchProducts(String keyword, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    Page<Product> findByPriceRange(double minPrice, double maxPrice, Pageable pageable);

    List<Product> findByQuantityLessThan(int quantity);

    long countByStatus(ProductStatus status);

    @Query("SELECT p.category.name, COUNT(p) FROM Product p GROUP BY p.category.name")
    Map<String, Long> countProductsByCategory();

    boolean existsByCategoryId(String categoryId);
}