package com.blubin.productservice.controller;

import com.blubin.productservice.repository.ProductImageRepository;
import com.blubin.productservice.service.ProductImageService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductImageController {
    private final ProductImageRepository productImageRepository;
    private final ProductImageService productImageService;

    public ProductImageController(ProductImageRepository productImageRepository, ProductImageService productImageService) {
        this.productImageRepository = productImageRepository;
        this.productImageService = productImageService;
    }


}
