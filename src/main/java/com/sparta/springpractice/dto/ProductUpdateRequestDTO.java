package com.sparta.springpractice.dto;

public record ProductUpdateRequestDTO(
        String name,
        Long price,
        Long stock
) {
}
