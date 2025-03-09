package com.blubin.productservice.viewmodel.brand;

import com.blubin.productservice.model.Brand;

import java.util.UUID;

public record BrandGetVm(UUID id, String name, String description) {
    public static BrandGetVm fromModel(Brand brand) {
        return new BrandGetVm(brand.getId(), brand.getBrandName(), brand.getBrandDescription());
    }
}
