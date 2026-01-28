package com.sparta.springpractice.dto;

import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

public record ProductResponseDTO(
        Long productId,
        String name,
        Long price,
        Long stock,
        LocalDateTime createdDate

) {
    @QueryProjection
    public ProductResponseDTO(
            Long productId,
            String name,
            Long price,
            Long stock,
            LocalDateTime createdDate)
    {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.createdDate = createdDate;
    }
}


