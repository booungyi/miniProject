package com.sparta.miniProject.controller;

import com.sparta.miniProject.dto.OrderCreateRequestDTO;
import com.sparta.miniProject.dto.OrderCreateResponseDTO;
import com.sparta.miniProject.dto.OrderResponseDTO;
import com.sparta.miniProject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    // 주문 생성
    @PostMapping
    public OrderCreateResponseDTO createOrder(
            @RequestBody OrderCreateRequestDTO request) {
        return orderService.createOrder(request);
    }

    //주문 단건 조회
    @GetMapping("/{orderId}")
    public OrderResponseDTO getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

    //주문 목록 조회
    @GetMapping
    public Page<OrderResponseDTO> getOrders(@PageableDefault(size = 20) Pageable pageable) {
        return orderService.getOrderList(pageable);
    }

    //주문 삭제 (softdelete)
    @DeleteMapping("/{orderId}")
    public void deleteOrderById (@PathVariable Long orderId) {
        orderService.deleteOrderById(orderId);
    }
}
