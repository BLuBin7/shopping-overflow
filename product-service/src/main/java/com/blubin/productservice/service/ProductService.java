package com.blubin.productservice.service;

import com.blubin.commonservice.exception.DuplicatedException;
import com.blubin.commonservice.exception.NotFoundException;
import com.blubin.productservice.model.Brand;
import com.blubin.productservice.model.Product;
import com.blubin.productservice.model.ProductCategory;
import com.blubin.productservice.repository.BrandRepository;
import com.blubin.productservice.repository.ProductCategoryRepository;
import com.blubin.productservice.repository.ProductRepository;
import com.blubin.productservice.utils.Constants;
import com.blubin.productservice.viewmodel.product.ProductGetVm;
import com.blubin.productservice.viewmodel.product.ProductListGerVm;
import com.blubin.productservice.viewmodel.product.ProductPostVm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    private boolean checkExistedName(String productName, UUID id) {
        return brandRepository.findExistedName(productName, id) != null;
    }

    private void validExistedName(String productName, UUID productId) {
        if(checkExistedName(productName, productId)) {
            throw new DuplicatedException(Constants.ErrorCodes.NAME_ALREADY_EXITED,productName);
        }
    }

    public Product createProduct(ProductPostVm productPostVm) {
        ProductCategory productCategory = productCategoryRepository.findById(productPostVm.productCategoryId()).orElseThrow();
        Brand brand = brandRepository.findById(productPostVm.brandId()).orElseThrow();
        Product product = productPostVm.toModel(productCategory, brand);

        return productRepository.save(product);
    }

    public ProductListGerVm getProductList(int pageNo, int pageSize) {
        List<ProductGetVm> productGetVms = new ArrayList<>();

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Product> productPage = productRepository.findAll(pageable);
        List<Product> productList = productPage.getContent();

        for (Product product : productList) {
            productGetVms.add(ProductGetVm.fromModel(product));
        }

        return new ProductListGerVm(
                productGetVms,
                productPage.getNumber(),
                productPage.getSize(),
                (int) productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.isLast()
        );
    }

    public Product updateProduct(UUID productId, ProductPostVm productPostVm) {
        validExistedName(productPostVm.productName(), productId);

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException(Constants.ErrorCodes.PRODUCT_NOT_FOUND, productId)
        );

        product.setProductName(productPostVm.productName());
        product.setProductDescription(productPostVm.productDescription());
        product.setModelHeight(productPostVm.modelHeight());
        product.setModelWearing(productPostVm.modelWearing());
        product.setCareInstructions(productPostVm.careInstructions());
        product.setAbout(productPostVm.about());

        Optional<ProductCategory> productCategory = productCategoryRepository.findById(productPostVm.productCategoryId());
        product.setProductCategory(productCategory.orElseThrow(
                () -> new NotFoundException(Constants.ErrorCodes.PRODUCT_NOT_FOUND, productId)
        ));

        Optional<Brand> brandId = brandRepository.findById(productPostVm.brandId());
        product.setBrand(brandId.orElseThrow(
                () -> new NotFoundException(Constants.ErrorCodes.BRAND_NOT_FOUND, brandId)
        ));

        return product;
    }


}
