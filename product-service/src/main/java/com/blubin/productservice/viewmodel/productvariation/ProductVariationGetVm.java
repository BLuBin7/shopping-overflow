package com.blubin.productservice.viewmodel.productvariation;

import com.blubin.productservice.model.ProductVariation;
import com.blubin.productservice.viewmodel.productitem.ProductItemGetVm;
import com.blubin.productservice.viewmodel.sizeoption.SizeOptionGetVm;

import java.util.UUID;

public record ProductVariationGetVm(UUID varitation_id,
                                    ProductItemGetVm productItemGetVm,
                                    SizeOptionGetVm sizeOptionGetVm,
                                    Long qtyInStock) {
    public static ProductVariationGetVm fromModel(ProductVariation productVariation) {
        return new ProductVariationGetVm(
                productVariation.getId(),
                productVariation.getProductItem() != null ?
                        ProductItemGetVm.fromModel(productVariation.getProductItem()) : null,
                productVariation.getSize() != null ?
                        SizeOptionGetVm.fromModel(productVariation.getSize()) : null,
                productVariation.getQtyInStock()
        );
    }
}
