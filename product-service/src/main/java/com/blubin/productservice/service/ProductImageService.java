package com.blubin.productservice.service;

import com.blubin.productservice.model.Brand;
import com.blubin.productservice.model.Product;
import com.blubin.productservice.model.ProductImage;
import com.blubin.productservice.model.ProductItem;
import com.blubin.productservice.repository.BrandRepository;
import com.blubin.productservice.repository.ProductImageRepository;
import com.blubin.productservice.repository.ProductItemRepository;
import com.blubin.productservice.repository.ProductRepository;
import com.blubin.productservice.viewmodel.product.ProductPostVm;
import com.blubin.productservice.viewmodel.productimage.ProductImagePostVm;
import org.springframework.stereotype.Service;

@Service
public class ProductImageService {
    private final ProductImageRepository productImageRepository;
    private final ProductRepository productRepository;
    private final ProductItemRepository productItemRepository;


    public ProductImageService(ProductImageRepository productImageRepository,
                               ProductRepository productRepository,
                               ProductItemRepository productItemRepository) {
        this.productImageRepository = productImageRepository;
        this.productRepository = productRepository;
        this.productItemRepository = productItemRepository;
    }


    public ProductImage create(ProductImagePostVm productImagePostVm) {
        Product product = productRepository.findById(productImagePostVm.productId()).orElse(null);
        ProductItem productItem = productItemRepository.findById(productImagePostVm.productItemId()).orElse(null);
        return productImageRepository.save(productImagePostVm.toModel(product, productItem));
    }


}
