package com.blubin.productservice.viewmodel.attribute.attributetype;

import com.blubin.productservice.model.AttributeType;
import net.logstash.logback.encoder.com.lmax.disruptor.dsl.ProducerType;

public record AttributeTypeGetVm(Long id, String attributeName) {
    public static AttributeTypeGetVm fromModel(AttributeType attributeType) {
        return new AttributeTypeGetVm(attributeType.getId(),attributeType.getAttributeName());
    }
}
