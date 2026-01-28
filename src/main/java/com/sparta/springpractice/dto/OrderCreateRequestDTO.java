package com.sparta.springpractice.dto;

public record OrderCreateRequestDTO(
        Long productId,
        Long quantity
) {
}
