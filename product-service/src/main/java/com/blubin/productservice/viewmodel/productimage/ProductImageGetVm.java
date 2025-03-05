package com.blubin.productservice.viewmodel.productimage;

import com.blubin.productservice.model.ProductImage;
import com.blubin.productservice.viewmodel.product.ProductGetVm;
import com.blubin.productservice.viewmodel.productitem.ProductItemGetVm;

public record ProductImageGetVm(
        Long productImageId,
        ProductGetVm product,
        String imageFilename,
        ProductItemGetVm productItem) {

    public static ProductImageGetVm fromModel(ProductImage productImage) {
        return new ProductImageGetVm(
                productImage.getId(),
                productImage.getProduct() != null ?
                        ProductGetVm.fromModel(productImage.getProduct()) : null,
                productImage.getImageFilename(),
                productImage.getProductItem() != null ?
                        ProductItemGetVm.fromModel(productImage.getProductItem()) : null
        );
    }
}
