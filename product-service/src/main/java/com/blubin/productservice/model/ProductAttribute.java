package com.blubin.productservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_attribute")
public class ProductAttribute {
    @EmbeddedId
    private ProductAttributeId id;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @MapsId("attributeOptionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "attribute_option_id", nullable = false)
    private AttributeOption attributeOption;

}