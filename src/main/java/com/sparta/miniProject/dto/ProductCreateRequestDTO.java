package com.sparta.miniProject.dto;

public record ProductCreateRequestDTO(
        String name,
        Long price,
        Long stock
) {
}
