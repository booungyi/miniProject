package com.sparta.springpractice.controller;

import com.sparta.springpractice.service.OrderService;
import com.sparta.springpractice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")

public class ProdcutController {

    private final ProductService productService;

}
