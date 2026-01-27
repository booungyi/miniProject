package com.sparta.springpractice.domain.order;

import com.sparta.springpractice.dto.OrderResponseDto;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface OrderRepositoryCustom {
    Page<OrderResponseDto> searchOrders(Pageable pageable);

}
