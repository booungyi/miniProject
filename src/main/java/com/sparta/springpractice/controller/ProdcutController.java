package com.sparta.springpractice.controller;

import com.sparta.springpractice.dto.ProductCreateRequestDTO;
import com.sparta.springpractice.dto.ProductCreateResponseDTO;
import com.sparta.springpractice.dto.ProductResponseDTO;
import com.sparta.springpractice.dto.ProductUpdateRequestDTO;
import com.sparta.springpractice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")

public class ProdcutController {

    private final ProductService productService;


    //상품 생성
    @PostMapping
    public ProductCreateResponseDTO createProduct(@RequestBody ProductCreateRequestDTO product) {
        return productService.create(product);
    }

    //상품 단일 조회
    @GetMapping("/{productId}")
    public ProductResponseDTO getProductById(@PathVariable Long productId) {
        return productService.findProductById(productId);
    }

    //상품 목록 조회 페이징
    @GetMapping
    public Page<ProductResponseDTO> updateProduct(Pageable pageable) {
        return productService.findAllProducts(pageable);
    }

    //상품 수정
    @PatchMapping("/{productId}")
    public ProductCreateResponseDTO updateProduct(@PathVariable Long productId,
                                                  @RequestBody ProductUpdateRequestDTO productDto) {
        return productService.updateProductById(productId, productDto);
    }
    //상품 삭제
    @PatchMapping("/{podcutId}")
    public void deleteProduct(@PathVariable Long podcutId) {
        productService.deleteProductById(podcutId);
    }

}
