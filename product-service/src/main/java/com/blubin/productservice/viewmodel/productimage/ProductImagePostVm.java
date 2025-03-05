package com.blubin.productservice.viewmodel.productimage;

import com.blubin.productservice.model.Product;
import com.blubin.productservice.model.ProductImage;
import com.blubin.productservice.model.ProductItem;

public record ProductImagePostVm(Long productImageId,
                                 Long productId,
                                 String imageFilename,
                                 Long productItemId) {
    public ProductImage toModel(Product product, ProductItem productItem) {
        ProductImage productImage = new ProductImage();
        productImage.setId(productImageId);
        productImage.setImageFilename(imageFilename);
        productImage.setProduct(product);
        productImage.setProductItem(productItem);
        return productImage;
    }
}
