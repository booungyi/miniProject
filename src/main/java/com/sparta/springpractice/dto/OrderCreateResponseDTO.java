package com.sparta.springpractice.dto;

import com.sparta.springpractice.domain.order.Order;

import java.time.LocalDateTime;

//생성시 조회 전용 응답DTO
public record OrderCreateResponseDTO(
        Long orderId,
        Long productId,
        String productName,
        Long quantity,
        LocalDateTime createdDate
) {
    public static OrderCreateResponseDTO from (Order order) {
        return new OrderCreateResponseDTO(order.getId(),
                order.getProduct().getId(),
                order.getProduct().getName(),
                order.getQuantity(),
                order.getCreateDate()
        );
    }

}
