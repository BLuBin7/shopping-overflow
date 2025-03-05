package com.blubin.productservice.viewmodel.product;

import com.blubin.productservice.model.Brand;
import com.blubin.productservice.model.Product;
import com.blubin.productservice.model.ProductCategory;

public record ProductPostVm(
        String productName,
        String productDescription,
        String modelHeight,
        String modelWearing,
        String careInstructions,
        String about,
        Long productCategoryId,
        Long brandId
) {
    public Product toModel(ProductCategory productCategory, Brand brand) {
        Product product = new Product();
        product.setProductName(this.productName);
        product.setProductDescription(this.productDescription);
        product.setModelHeight(this.modelHeight);
        product.setModelWearing(this.modelWearing);
        product.setCareInstructions(this.careInstructions);
        product.setAbout(this.about);
        product.setProductCategory(productCategory);
        product.setBrand(brand);
        return product;
    }
}
