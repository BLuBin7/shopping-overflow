package com.blubin.productservice.viewmodel.sizeoption;

import com.blubin.productservice.model.SizeOption;
import com.blubin.productservice.viewmodel.sizecategory.SizeCategoryGetVm;

import java.util.UUID;

public record SizeOptionGetVm(UUID sizeOptionId,
                              String sizeName,
                              Integer sortOrder,
                              SizeCategoryGetVm sizeCategoryGetVm) {
    public static SizeOptionGetVm fromModel(SizeOption sizeOption) {
        return new SizeOptionGetVm(
                sizeOption.getId(),
                sizeOption.getSizeName(),
                sizeOption.getSortOrder(),
                sizeOption.getSizeCategory() != null ?
                        SizeCategoryGetVm.fromModel(sizeOption.getSizeCategory()) : null
        );
    }
}
