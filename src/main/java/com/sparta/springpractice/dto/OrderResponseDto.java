package com.sparta.springpractice.dto;

import java.time.LocalDateTime;

public record OrderResponseDto(
        Long orderId,
        Long productId,
        String productName,
        int quantity,
        LocalDateTime createDate
) {
}
