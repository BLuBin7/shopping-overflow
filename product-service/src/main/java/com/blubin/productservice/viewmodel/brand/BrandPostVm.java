package com.blubin.productservice.viewmodel.brand;

import com.blubin.productservice.model.Brand;

import java.util.UUID;

public record BrandPostVm(UUID id, String name, String description) {
    public Brand toModel(){
        Brand brand = new Brand();
        brand.setId(id);
        brand.setBrandName(name);
        brand.setBrandDescription(description);
        return brand;
    }
}
