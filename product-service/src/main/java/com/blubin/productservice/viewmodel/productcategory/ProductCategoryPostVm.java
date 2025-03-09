package com.blubin.productservice.viewmodel.productcategory;

import com.blubin.productservice.model.ProductCategory;

import java.util.UUID;

public record ProductCategoryPostVm(UUID id, String categoryName, String categoryImage, String categoryDescription) {
    public ProductCategory toModel(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(id);
        productCategory.setCategoryName(categoryName);
        productCategory.setCategoryImage(categoryImage);
        productCategory.setCategoryDescription(categoryDescription);
        return productCategory;
    }
}
