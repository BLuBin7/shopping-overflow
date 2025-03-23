package com.blubin.productservice.viewmodel.product;

import java.util.List;

public record ProductListGerVm (
        List<ProductGetVm> products,
        int pageNo,
        int pageSize,
        int totalElements,
        int totalPages,
        boolean isLastPage
) {
}
