package com.blubin.productservice.viewmodel.attribute.attributeoption;

import com.blubin.productservice.model.AttributeOption;
import com.blubin.productservice.model.AttributeType;

import java.util.UUID;

public record AttributeOptionPostVm(UUID id,
                                    UUID attributeTypeId,
                                    String attributeOptionName) {
    public AttributeOption toModel(AttributeType attributeType) {
        AttributeOption attributeOption = new AttributeOption();
        attributeOption.setId(this.id);
        attributeOption.setAttributeType(attributeType);
        attributeOption.setAttributeOptionName(this.attributeOptionName);
        return attributeOption;
    }

}
