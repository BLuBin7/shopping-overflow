package com.blubin.productservice.viewmodel.sizecategory;

import com.blubin.productservice.model.SizeCategory;

public record SizeCategoryPostVm(String sizeCategoryName) {
    public SizeCategory toModel(){
        SizeCategory sizeCategory = new SizeCategory();
        sizeCategory.setCategoryName(this.sizeCategoryName);
        return sizeCategory;
    }
}
