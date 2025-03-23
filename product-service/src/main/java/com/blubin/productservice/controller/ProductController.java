package com.blubin.productservice.controller;

import com.blubin.productservice.model.Product;
import com.blubin.productservice.service.ProductService;
import com.blubin.productservice.utils.PageableConstant;
import com.blubin.productservice.viewmodel.error.ErrorVm;
import com.blubin.productservice.viewmodel.product.ProductGetVm;
import com.blubin.productservice.viewmodel.product.ProductListGerVm;
import com.blubin.productservice.viewmodel.product.ProductPostVm;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jdk.jfr.ValueDescriptor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
public class ProductController {
    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/backoffice/products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(schema = @Schema(implementation = ProductGetVm.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorVm.class)))})
    public ResponseEntity<ProductGetVm> createProduct(
            @Valid @RequestBody ProductPostVm productPostVm,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Product product = productService.createProduct(productPostVm);
        return ResponseEntity.created(uriComponentsBuilder.replacePath("/products/{id}")
                        .buildAndExpand(product.getId()).toUri())
                .body(ProductGetVm.fromModel(product));
    }

    @GetMapping("/backoffice/products/paging")
    public ResponseEntity<ProductListGerVm> getPagingProducts(
            @RequestParam(value = "pageNo", defaultValue = PageableConstant.DEFAULT_PAGE_NUMBER, required = false)
            int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageableConstant.DEFAULT_PAGE_SIZE, required = false)
            int pageSize
    ){
        return ResponseEntity.ok(productService.getProductList(pageNo, pageSize));
    }


    @PutMapping("/backoffice/products/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = ErrorVm.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorVm.class)))})
    public ResponseEntity<Void> updateProduct(@PathVariable UUID id, @Valid @RequestBody ProductPostVm productPostVm) {
        productService.updateProduct(id,productPostVm);
        return ResponseEntity.noContent().build();
    }



}
