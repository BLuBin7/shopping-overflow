package com.blubin.productservice.viewmodel.product;

import com.blubin.productservice.model.Product;
import com.blubin.productservice.viewmodel.brand.BrandGetVm;
import com.blubin.productservice.viewmodel.productcategory.ProductCategoryGetVm;

import java.util.UUID;

public record ProductGetVm(
        UUID id,
        String productName,
        ProductCategoryGetVm productCategory,
        String productDescription,
        BrandGetVm brand,
        String modelHeight,
        String modelWearing,
        String careInstructions,
        String about
) {
    public static ProductGetVm fromModel(Product product) {
        return new ProductGetVm(
                product.getId(),
                product.getProductName(),
                product.getProductCategory() != null ?
                        ProductCategoryGetVm.fromModel(product.getProductCategory()) : null,
                product.getProductDescription(),
                product.getBrand() != null ?
                        BrandGetVm.fromModel(product.getBrand()) : null,
                product.getModelHeight(),
                product.getModelWearing(),
                product.getCareInstructions(),
                product.getAbout()
        );
    }
}