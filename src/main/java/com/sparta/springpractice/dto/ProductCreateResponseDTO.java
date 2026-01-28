package com.sparta.springpractice.dto;

import com.sparta.springpractice.domain.product.Product;

public record ProductCreateResponseDTO(
        String name,
        Long price,
        Long stock
) {
    public static ProductCreateResponseDTO from(Product product) {
        return new ProductCreateResponseDTO(
                product.getName(),
                product.getPrice(),
                product.getStock()
        );
    }
}

