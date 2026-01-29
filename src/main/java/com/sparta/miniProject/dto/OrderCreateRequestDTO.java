package com.sparta.miniProject.dto;

public record OrderCreateRequestDTO(
        Long productId,
        Long quantity
) {
}
