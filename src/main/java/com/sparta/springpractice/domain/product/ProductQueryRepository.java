package com.sparta.springpractice.domain.product;

import com.sparta.springpractice.dto.ProductCreateResponseDTO;
import com.sparta.springpractice.dto.ProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductQueryRepository {
    ProductResponseDTO findProductResponseById(Long productId);
    Page<ProductResponseDTO> searchProducts(Pageable pageable);
}
