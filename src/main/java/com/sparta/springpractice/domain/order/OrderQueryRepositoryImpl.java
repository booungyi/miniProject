package com.sparta.springpractice.domain.order;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.springpractice.dto.OrderResponseDTO;
import com.sparta.springpractice.dto.QOrderResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.support.PageableExecutionUtils;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import static com.sparta.springpractice.domain.order.QOrder.order;
import static com.sparta.springpractice.domain.product.QProduct.product;
@Repository
@RequiredArgsConstructor
public class OrderQueryRepositoryImpl implements OrderQueryRepository {

    private final JPAQueryFactory queryFactory;

    private BooleanExpression notDeleted() {
        return order.deleted.isFalse();
    }
    //주문 단건 조회
    public OrderResponseDTO findOrderResponseById(Long orderId) {
        return queryFactory
                .select(new QOrderResponseDTO(
                        order.id,
                        order.product.id,
                        order.product.name,
                        order.quantity,
                        order.createDate
                ))
                .from(order)
                .join(order.product, product)
                .where(order.id.eq(orderId),
                        notDeleted())
//                        order.delete.isFalse)
                .fetchOne();
    }


    @Override
    public Page<OrderResponseDTO> searchOrders(Pageable pageable) {
        // 1. DTO로 직접 조회 (N+1 문제 원천 차단)
        // Order와 Product를 Join하여 필요한 데이터만 한번에 가져옵니다.
        List<OrderResponseDTO> content = queryFactory
                .select(new QOrderResponseDTO(
                        order.id,
                        order.product.id,
                        order.product.name, // 상품 이름 바로 가져오기 (Join)
                        order.quantity,
                        order.createDate))
                .from(order)
                .join(order.product, product)
                .where(notDeleted())// Lazy Loading 무시하고 바로 Join해서 가져옴
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
