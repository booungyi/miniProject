package com.sparta.springpractice.dto;

import com.sparta.springpractice.domain.product.Product;

public record ProductResponseDTO(
        String name,
        int price,
        int stock
) {
    public static ProductResponseDTO from(Product product) {
        return new ProductResponseDTO(
                product.getName(),
                product.getPrice(),
                product.getStock()
        );
    }
}

