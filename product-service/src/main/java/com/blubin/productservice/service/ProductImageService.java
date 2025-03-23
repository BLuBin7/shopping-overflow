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
import com.blubin.productservice.viewmodel.productimage.ProductImageGetVm;
import com.blubin.productservice.viewmodel.productimage.ProductImageListGetVm;
import com.blubin.productservice.viewmodel.productimage.ProductImagePostVm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public ProductImageListGetVm getProductImageList(int pageNo, int pageSize){
        List<ProductImageGetVm> productImageGetVms = new ArrayList<>();

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<ProductImage> productImagePage = productImageRepository.findAll(pageable);
        List<ProductImage> productImageList = productImagePage.getContent();

        for(ProductImage productImage : productImageList){
            productImageGetVms.add(ProductImageGetVm.fromModel(productImage));
        }

        return new ProductImageListGetVm(
                productImageGetVms,
                productImagePage.getNumber(),
                productImagePage.getSize(),
                (int) productImagePage.getTotalElements(),
                productImagePage.getTotalPages(),
                productImagePage.isLast()
        );
    }
}
