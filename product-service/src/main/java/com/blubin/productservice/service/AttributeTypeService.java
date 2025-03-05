package com.blubin.productservice.service;

import com.blubin.productservice.model.AttributeType;
import com.blubin.productservice.repository.AttributeTypeRepository;
import com.blubin.productservice.viewmodel.attribute.attributetype.AttributeTypePostVm;
import org.springframework.stereotype.Service;

@Service
public class AttributeTypeService {
    private final AttributeTypeRepository attributeTypeRepository;

    public AttributeTypeService(AttributeTypeRepository attributeTypeRepository) {
        this.attributeTypeRepository = attributeTypeRepository;
    }

    public AttributeType create(AttributeTypePostVm attributeTypePostVm) {
        return attributeTypeRepository.save(attributeTypePostVm.toModel());
    }
}
