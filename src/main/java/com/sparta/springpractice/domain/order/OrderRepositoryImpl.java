package com.sparta.springpractice.domain.order;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.springpractice.dto.OrderResponseDto;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.support.PageableExecutionUtils;

import java.awt.print.Pageable;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public OrderRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<OrderResponseDto> searchOrders(Pageable pageable) {
        // 1. DTO로 직접 조회 (N+1 문제 원천 차단)
        // Order와 Product를 Join하여 필요한 데이터만 한번에 가져옵니다.
        List<OrderResponseDto> content = queryFactory
                .select(new QOrderResponseDto(
                        order.id,
                        order.product.id,
                        order.product.name, // 상품 이름 바로 가져오기 (Join)
                        order.quantity,
                        order.createdDate))
                .from(order)
                .join(order.product, product) // Lazy Loading 무시하고 바로 Join해서 가져옴
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 2. Count Query 최적화
        JPAQuery<Long> countQuery = queryFactory
                .select(order.count())
                .from(order);
        // Count 셀 때는 조인 불필요하므로 제거하여 성능 최적화

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}
