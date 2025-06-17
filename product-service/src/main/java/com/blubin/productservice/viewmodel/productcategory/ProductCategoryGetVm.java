package com.blubin.productservice.viewmodel.productcategory;

import com.blubin.productservice.model.ProductCategory;

import java.util.UUID;

public record ProductCategoryGetVm (UUID id,
                                    String categoryName,
                                    String categoryImage,
                                    String categoryDescription) {
    public static ProductCategoryGetVm fromModel(ProductCategory productCategory) {
        return new ProductCategoryGetVm(productCategory.getId(),productCategory.getCategoryName(),
                productCategory.getCategoryImage(),productCategory.getCategoryDescription());
    }
}

