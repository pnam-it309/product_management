package com.example.BE.core.product.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
public class ProductsStatsResponse {
    private long totalProducts;
    private long availableProducts;
    private long lowStockProducts;
    private Map<String, Long> productsByCategory;
}
