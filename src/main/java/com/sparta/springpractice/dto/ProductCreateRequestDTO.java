package com.sparta.springpractice.dto;

public record ProductCreateRequestDTO(
        String name,
        int price,
        int stock
) {
}
