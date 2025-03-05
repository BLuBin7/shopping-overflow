package com.blubin.productservice.service;

import com.blubin.productservice.model.Brand;
import com.blubin.productservice.model.Product;
import com.blubin.productservice.model.ProductCategory;
import com.blubin.productservice.repository.BrandRepository;
import com.blubin.productservice.repository.ProductCategoryRepository;
import com.blubin.productservice.repository.ProductRepository;
import com.blubin.productservice.viewmodel.product.ProductPostVm;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final BrandRepository brandRepository;

    public ProductService(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository, BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.brandRepository = brandRepository;
    }


    public Product create(ProductPostVm productPostVm) {
        ProductCategory productCategory = productCategoryRepository.findById(productPostVm.productCategoryId()).orElseThrow();
        Brand brand = brandRepository.findById(productPostVm.brandId()).orElseThrow();
        Product product = productPostVm.toModel(productCategory, brand);

        return productRepository.save(productPostVm.toModel(productCategory, brand));
    }

}
