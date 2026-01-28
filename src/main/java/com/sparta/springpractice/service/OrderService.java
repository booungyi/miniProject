package com.sparta.springpractice.service;

import com.sparta.springpractice.domain.order.Order;
import com.sparta.springpractice.domain.order.OrderQueryRepository;
import com.sparta.springpractice.domain.order.OrderRepository;
import com.sparta.springpractice.domain.product.Product;
import com.sparta.springpractice.domain.product.ProductRepository;
import com.sparta.springpractice.dto.OrderCreateRequestDTO;
import com.sparta.springpractice.dto.OrderCreateResponseDTO;
import com.sparta.springpractice.dto.OrderResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderQueryRepository orderQueryRepository;
    //주문 생성
    @Transactional
    public OrderCreateResponseDTO createOrder(OrderCreateRequestDTO request) {
        // 원자성 확보를 위해 비관적 락 사용하여 조회
        // select for update 쿼리가 나가서 , 트랙잭션 종료시 까지 다른 트랜젝션이 건들지 못하게 함
        Product product = productRepository.findByIdWithPessimisticLock(request.productId()).
                orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));

        product.decreaseStock(request.quantity());

        Order order = new Order(product, request.quantity());
        Order savedOrder = orderRepository.save(order);

        return OrderCreateResponseDTO.from(savedOrder);
    }

    //주문 단건 조회
    @Transactional
    public OrderResponseDTO getOrder(Long orderId) {
        return orderQueryRepository.findOrderResponseById(orderId);
    }

    //주문 목록 조회
    @Transactional
    public Page<OrderResponseDTO> getOrderList(Pageable pageable) {
        return orderQueryRepository.searchOrders(pageable);
    }

    //주문 수정 ??

    //주문 삭제? 취소
    @Transactional
    public void deleteOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new IllegalArgumentException("주문이 존재하지 않습니다."));
        order.softDelete();
    }
}
