package com.blubin.productservice.viewmodel.productimage;

import com.blubin.productservice.model.ProductImage;

import java.util.List;

public record ProductImageListGetVm(
        List<ProductImageGetVm> productImages,
        int pageNo,
        int pageSize,
        int totalElements,
        int totalPages,
        boolean isLastPage
) {
}
