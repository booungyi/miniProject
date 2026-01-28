package com.sparta.springpractice.dto;

import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

// 여기서는 조회 응답용
public record OrderResponseDTO(
        Long orderId,
        Long productId,
        String productName,
        Long quantity,
        LocalDateTime createdDate
)
{ @QueryProjection // QueryDSL Q-Type 생성
    public OrderResponseDTO(
            Long orderId,
            Long productId,
            String productName,
            Long quantity,
            LocalDateTime createdDate)
    {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.createdDate = createdDate;
    }
}
