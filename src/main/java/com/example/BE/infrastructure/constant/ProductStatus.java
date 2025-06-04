package com.example.BE.infrastructure.constant;

public enum ProductStatus {
    SELLING("Đang bán"),
    OUT_OF_STOCK("Hết hàng"),
    DISCONTINUED("Ngừng kinh doanh");

    private final String displayName;

    ProductStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
