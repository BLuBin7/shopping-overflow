package com.blubin.productservice.controller;

import com.blubin.productservice.model.Product;
import com.blubin.productservice.service.ProductService;
import com.blubin.productservice.viewmodel.error.ErrorVm;
import com.blubin.productservice.viewmodel.product.ProductGetVm;
import com.blubin.productservice.viewmodel.product.ProductPostVm;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
        Product product = productService.create(productPostVm);
        return ResponseEntity.created(uriComponentsBuilder.replacePath("/products/{id}")
                        .buildAndExpand(product.getId()).toUri())
                .body(ProductGetVm.fromModel(product));
    }


}
