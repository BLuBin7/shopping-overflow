package com.blubin.productservice.viewmodel.attribute.attributetype;

import com.blubin.productservice.model.AttributeType;

import java.util.UUID;

public record AttributeTypePostVm(UUID id, String attributeName) {
    public AttributeType toModel(){
        AttributeType attributeType = new AttributeType();
        attributeType.setId(this.id);
        attributeType.setAttributeName(this.attributeName);
        return attributeType;
    }
}
