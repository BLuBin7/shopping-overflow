package com.blubin.productservice.viewmodel.productvariation;

import com.blubin.productservice.model.ProductItem;
import com.blubin.productservice.model.ProductVariation;
import com.blubin.productservice.model.SizeOption;

import java.util.UUID;

public record ProductVariationPostVm (UUID productItemId,
                                      UUID sizeOptionId,
                                      Long qtyInStock) {
    public ProductVariation toModel(ProductItem productItem, SizeOption sizeOption) {
        ProductVariation productVariation = new ProductVariation();
        productVariation.setProductItem(productItem);
        productVariation.setSize(sizeOption);
        productVariation.setQtyInStock(this.qtyInStock);
        return productVariation;
    }
}
