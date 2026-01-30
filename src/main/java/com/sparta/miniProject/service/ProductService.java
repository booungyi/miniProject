package com.sparta.miniProject.service;

import com.sparta.miniProject.domain.product.Product;
import com.sparta.miniProject.domain.product.ProductQueryRepository;
import com.sparta.miniProject.domain.product.ProductRepository;
import com.sparta.miniProject.dto.ProductCreateRequestDTO;
import com.sparta.miniProject.dto.ProductCreateResponseDTO;
import com.sparta.miniProject.dto.ProductResponseDTO;
import com.sparta.miniProject.dto.ProductUpdateRequestDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductQueryRepository productQueryRepository;

    //상품 생성(등록)
    @Transactional
    public ProductCreateResponseDTO create(ProductCreateRequestDTO productDto) {
        Product savedProduct = productRepository.save(
                new Product(productDto.name(), productDto.price(), productDto.stock())
        );
        return ProductCreateResponseDTO.from(savedProduct);
    }

    //상품 조회(전체 + 페이징 고려)
    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> findAllProducts(Pageable pageable) {
        return productQueryRepository.searchProducts(pageable);
    }

    //상품 단일 조회
    @Transactional(readOnly = true)
    public ProductResponseDTO findProductById(Long id) {
        return productQueryRepository.findProductResponseById(id);
    }

    //Todo 상품 수정 (상품요청)
    @Transactional
    public ProductCreateResponseDTO updateProductById(Long id, ProductUpdateRequestDTO productDto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find Id"));
        product.update(productDto.name(), productDto.price(), productDto.stock());
        return ProductCreateResponseDTO.from(product);
    }
    // 상품 삭제 softDelete
    @Transactional
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
