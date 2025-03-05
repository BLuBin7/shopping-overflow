package com.blubin.productservice.viewmodel.productcategory;

import com.blubin.productservice.model.ProductCategory;

public record ProductCategoryPostVm(Long id, String categoryName, String categoryImage, String categoryDescription) {
    public ProductCategory toModel(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(id);
        productCategory.setCategoryName(categoryName);
        productCategory.setCategoryImage(categoryImage);
        productCategory.setCategoryDescription(categoryDescription);
        return productCategory;
    }
}
