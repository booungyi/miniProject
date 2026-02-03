package com.sparta.miniProject.domain.order;

import com.sparta.miniProject.domain.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    // 주문 수량
    private Long quantity;
    //주문 일자
    private LocalDateTime createDate;
    //취소 상태
    private boolean deleted = false;
    //취소 일자 (soft delete)

    @CreationTimestamp
    @CreatedBy
    private LocalDateTime deletedAt;
    //주문 가격
    private  Long totalPrice;

    public Order(Product product, Long quantity) {
        this.product = product;
        this.quantity = quantity;
        this.createDate = LocalDateTime.now();
    }

    public void softDelete() {
        this.deleted = true;
        this.deletedAt = LocalDateTime.now();
    }
    public Long calculateTotalPrice() {
        return this.totalPrice = this.quantity*product.getPrice();
    }

}
