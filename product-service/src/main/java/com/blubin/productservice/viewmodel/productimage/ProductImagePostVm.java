package com.blubin.productservice.viewmodel.productimage;

import com.blubin.productservice.model.Product;
import com.blubin.productservice.model.ProductImage;
import com.blubin.productservice.model.ProductItem;

import java.util.UUID;

public record ProductImagePostVm(UUID productImageId,
                                 UUID productId,
                                 String imageFilename,
                                 UUID productItemId) {
    public ProductImage toModel(Product product, ProductItem productItem) {
        ProductImage productImage = new ProductImage();
        productImage.setId(productImageId);
        productImage.setImageFilename(imageFilename);
        productImage.setProduct(product);
        productImage.setProductItem(productItem);
        return productImage;
    }
}
