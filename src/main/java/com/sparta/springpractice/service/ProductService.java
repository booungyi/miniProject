package com.sparta.springpractice.service;

import com.sparta.springpractice.domain.product.Product;
import com.sparta.springpractice.domain.product.ProductRepository;
import com.sparta.springpractice.dto.ProductCreateRequestDTO;
import com.sparta.springpractice.dto.ProductResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.ProtectionDomain;


@RequiredArgsConstructor
@Service
public class ProductService {
    public final ProductRepository productRepository;

    //상품 생성(등록)
    @Transactional
    public ProductResponseDTO save(ProductCreateRequestDTO productDto) {
        Product savedProduct = productRepository.save(
                new Product(productDto.name(), productDto.price(), productDto.stock())
        );
        return ProductResponseDTO.from(savedProduct);
    }

    //상품
}
