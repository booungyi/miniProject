package com.sparta.miniProject.dto;

public record ProductCreateRequestDTO(
        String productName,
        Long price,
        Long stock
) {
}
