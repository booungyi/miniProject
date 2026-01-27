package com.sparta.springpractice.domain.order;

import com.sparta.springpractice.domain.product.Product;
import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Lazy;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity; // 주문 수량 (이번 과제는 1로 고정된다)

    private LocalDateTime createDate;

    public Order(Product product, int quantity, LocalDateTime createDate) {
        this.product = product;
        this.quantity = quantity;
        this.createDate = createDate;
    }

}
