package com.blubin.productservice.viewmodel.colour;

import java.util.List;

public record ColourListGetVm(
        List<ColourGetVm> colours,
        int pageNo,
        int pageSize,
        int totalElements,
        int totalPages,
        boolean isLastPage
    ) {
}
