package com.blubin.productservice.viewmodel.productcategory;

import java.util.List;

public record ProductCategoryListGerVm(
        List<ProductCategoryGetVm> productcategorys,
        int pageNo,
        int pageSize,
        int totalElements,
        int totalPages,
        boolean isLastPage
) {
}
