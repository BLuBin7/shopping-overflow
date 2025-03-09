package com.blubin.productservice.viewmodel.productitem;

import com.blubin.productservice.model.Colour;
import com.blubin.productservice.model.Product;
import com.blubin.productservice.model.ProductItem;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductItemPostVm(UUID id,
                                UUID productId,
                                UUID colourId,
                                BigDecimal originalPrice,
                                BigDecimal salePrice,
                                String productCode) {
    public ProductItem toModel( Product product, Colour colour){
        ProductItem productItem = new ProductItem();
        productItem.setId(product.getId());
        productItem.setProduct(product);
        productItem.setColour(colour);
        productItem.setOriginalPrice(originalPrice);
        productItem.setSalePrice(salePrice);
        productItem.setProductCode(productCode);
        return productItem;
    }

}
