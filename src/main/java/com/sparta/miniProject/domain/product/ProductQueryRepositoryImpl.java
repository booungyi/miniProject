package com.sparta.miniProject.domain.product;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.miniProject.dto.ProductResponseDTO;
import com.sparta.miniProject.dto.QProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sparta.miniProject.domain.order.QOrder.order;
import static com.sparta.miniProject.domain.product.QProduct.product;
@Repository
@RequiredArgsConstructor

public class ProductQueryRepositoryImpl implements ProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    private BooleanExpression notDeleted() {
        return product.deleted.isFalse();
    }

    //상품 단건 조회
    public ProductResponseDTO findProductResponseById(Long productId) {
        return queryFactory
                .select(new QProductResponseDTO(
                        product.id,
                        product.name,
                        product.price,
                        product.stock,
                        product.createdDate
                ))
                .from(product)
                .where(product.id.eq(productId),
                        notDeleted())
                .fetchOne();
    }

    //상품 목록 조회
    public Page<ProductResponseDTO> searchProducts(Pageable pageable) {
        List<ProductResponseDTO> response= queryFactory
                .select(new QProductResponseDTO(
                        product.id,
                        product.name,
                        product.price,
                        product.stock,
                        product.createdDate

                )).from(product)
                .where(notDeleted())// Lazy Loading 무시하고 바로 Join해서 가져옴
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        // 2. Count Query 최적화
        JPAQuery<Long> countQuery = queryFactory
                .select(order.count())
                .from(order);
        // Count 셀 때는 조인 불필요하므로 제거하여 성능 최적화

        return PageableExecutionUtils.getPage(response, pageable, countQuery::fetchOne);
    }

}
