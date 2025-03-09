package com.blubin.productservice.viewmodel.attribute.attributeoption;

import com.blubin.productservice.model.AttributeOption;
import com.blubin.productservice.viewmodel.attribute.attributetype.AttributeTypeGetVm;

import java.util.UUID;

public record AttributeOptionGetVm(UUID id,
                                   AttributeTypeGetVm attributeTypeGetVm,
                                   String attributeOptionName) {
    public static AttributeOptionGetVm fromModel(AttributeOption attributeOption) {
        return new AttributeOptionGetVm(
                attributeOption.getId(),
                attributeOption.getAttributeType() != null ?
                        AttributeTypeGetVm.fromModel(attributeOption.getAttributeType()) : null,
                attributeOption.getAttributeOptionName()
        );
    }

}
