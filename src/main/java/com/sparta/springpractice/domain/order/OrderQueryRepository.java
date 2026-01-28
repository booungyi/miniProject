package com.sparta.springpractice.domain.order;

import com.sparta.springpractice.dto.OrderResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderQueryRepository  {
    OrderResponseDTO findOrderResponseById(Long orderId);
    Page<OrderResponseDTO> searchOrders(Pageable pageable);
}
