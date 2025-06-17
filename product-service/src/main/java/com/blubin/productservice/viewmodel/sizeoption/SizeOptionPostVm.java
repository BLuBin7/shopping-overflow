package com.blubin.productservice.viewmodel.sizeoption;

import com.blubin.productservice.model.SizeCategory;
import com.blubin.productservice.model.SizeOption;

import java.util.UUID;

public record SizeOptionPostVm(String sizeName,
                               Integer sortOrder,
                               UUID sizeCategoryId) {
    public SizeOption toModel(SizeCategory sizeCategory) {
        SizeOption sizeOption = new SizeOption();
        sizeOption.setSizeName(this.sizeName);
        sizeOption.setSortOrder(this.sortOrder);
        sizeOption.setSizeCategory(sizeCategory);
        return sizeOption;
    }
}
