package com.sparta.miniProject.dto;

import com.sparta.miniProject.domain.product.Product;

import java.time.LocalDateTime;

public record ProductCreateResponseDTO(
        Long id,
        String name,
        Long price,
        Long stock,
        LocalDateTime createdDate
) {
    public static ProductCreateResponseDTO from(Product product) {
        return new ProductCreateResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getCreatedDate()
        );
    }
}

