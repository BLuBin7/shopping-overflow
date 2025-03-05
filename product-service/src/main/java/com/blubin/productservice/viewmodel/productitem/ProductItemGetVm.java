package com.blubin.productservice.viewmodel.productitem;

import com.blubin.productservice.model.ProductItem;
import com.blubin.productservice.viewmodel.colour.ColourGetVm;
import com.blubin.productservice.viewmodel.product.ProductGetVm;

import java.math.BigDecimal;

public record ProductItemGetVm(Long productId,
                               ProductGetVm productGetVm,
                               ColourGetVm colourGetVm,
                               BigDecimal originalPrice,
                               BigDecimal salePrice,
                               String productCode){
    public static ProductItemGetVm fromModel(ProductItem productItem) {
        return new ProductItemGetVm(
                productItem.getId(),
                productItem.getProduct() != null?
                        ProductGetVm.fromModel(productItem.getProduct()) : null,
                productItem.getColour() != null?
                        ColourGetVm.fromModel(productItem.getColour()) : null,
                productItem.getOriginalPrice(),
                productItem.getSalePrice(),
                productItem.getProductCode()
                );
    }

}
