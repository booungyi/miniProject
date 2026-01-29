package com.sparta.miniProject.domain.product;

import com.sparta.miniProject.dto.ProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductQueryRepository {
    ProductResponseDTO findProductResponseById(Long productId);
    Page<ProductResponseDTO> searchProducts(Pageable pageable);
}
