package com.sparta.miniProject.domain.order;

import com.sparta.miniProject.dto.OrderResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderQueryRepository  {
    OrderResponseDTO findOrderResponseById(Long orderId);
    Page<OrderResponseDTO> searchOrders(Pageable pageable);
}
