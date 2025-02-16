package com.blubin.productservice.viewmodel.brand;

import java.util.List;

public record BrandListGetVm(
        List<BrandGetVm> brands,
        int pageNo,
        int pageSize,
        int totalElements,
        int totalPages,
        boolean isLastPage
    ) {
}
