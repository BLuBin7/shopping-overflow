package com.blubin.productservice.controller;

import com.blubin.productservice.model.ProductCategory;
import com.blubin.productservice.repository.ProductCategoryRepository;
import com.blubin.productservice.service.ProductCategoryService;
import com.blubin.productservice.viewmodel.error.ErrorVm;
import com.blubin.productservice.viewmodel.productcategory.ProductCategoryGetVm;
import com.blubin.productservice.viewmodel.productcategory.ProductCategoryPostVm;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class ProductCategoryController {
    private static final Logger log = LoggerFactory.getLogger(ColourController.class);
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryService productCategoryService;


    public ProductCategoryController(ProductCategoryRepository productCategoryRepository, ProductCategoryService productCategoryService) {
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryService = productCategoryService;
    }

    @PostMapping("/backoffice/product_categorys")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(schema = @Schema(implementation = ProductCategoryGetVm.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorVm.class)))})
    public ResponseEntity<ProductCategoryGetVm> createproductCategory(
            @Valid @RequestBody ProductCategoryPostVm productCategoryPostVm,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        ProductCategory productCategory = productCategoryService.create(productCategoryPostVm);
        return ResponseEntity.created(uriComponentsBuilder.replacePath("/product_categorys/{id}")
                        .buildAndExpand(productCategory.getId()).toUri())
                .body(ProductCategoryGetVm.fromModel(productCategory));
    }
}
