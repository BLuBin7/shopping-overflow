package com.blubin.productservice.viewmodel.sizecategory;

import com.blubin.productservice.model.SizeCategory;

import java.util.UUID;

public record SizeCategoryGetVm(UUID sizeCategoryId, String sizeCategoryName) {
    public static SizeCategoryGetVm fromModel(SizeCategory sizecategory) {
        return new SizeCategoryGetVm(
                sizecategory.getId(),
                sizecategory.getCategoryName()
        );
    }
}
