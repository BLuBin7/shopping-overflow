package com.blubin.productservice.viewmodel.attribute.attributeoption;

import com.blubin.productservice.model.AttributeOption;
import com.blubin.productservice.model.AttributeType;

public record AttributeOptionPostVm(Long id,
                                    Long attributeTypeId,
                                    String attributeOptionName) {
    public AttributeOption toModel(AttributeType attributeType) {
        AttributeOption attributeOption = new AttributeOption();
        attributeOption.setId(this.id);
        attributeOption.setAttributeType(attributeType);
        attributeOption.setAttributeOptionName(this.attributeOptionName);
        return attributeOption;
    }

}
