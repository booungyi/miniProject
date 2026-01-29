package com.sparta.miniProject.dto;

public record ProductUpdateRequestDTO(
        String name,
        Long price,
        Long stock
) {
}
