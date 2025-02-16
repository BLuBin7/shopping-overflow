package com.blubin.productservice.controller;

import com.blubin.commonservice.exception.BadRequestException;
import com.blubin.commonservice.exception.NotFoundException;
import com.blubin.productservice.model.Brand;
import com.blubin.productservice.repository.BrandRepository;
import com.blubin.productservice.service.BrandService;
import com.blubin.productservice.utils.Constants;
import com.blubin.productservice.utils.PageableConstant;
import com.blubin.productservice.viewmodel.brand.BrandGetVm;
import com.blubin.productservice.viewmodel.brand.BrandListGetVm;
import com.blubin.productservice.viewmodel.brand.BrandPostVm;
import com.blubin.productservice.viewmodel.error.ErrorVm;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class BrandController {
    private static final Logger log = LoggerFactory.getLogger(BrandController.class);
    private final BrandRepository brandRepository;
    private final BrandService brandService;

    public BrandController(BrandRepository brandRepository, BrandService brandService) {
        this.brandRepository = brandRepository;
        this.brandService = brandService;
    }

    @PostMapping("/backoffice/brands")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(schema = @Schema(implementation = BrandGetVm.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorVm.class)))})
    public ResponseEntity<BrandGetVm> createBrand(
            @Valid @RequestBody BrandPostVm brandPostVm,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Brand brand = brandService.create(brandPostVm);
        return ResponseEntity.created(uriComponentsBuilder.replacePath("/brands/{id}")
                        .buildAndExpand(brand.getId()).toUri())
                .body(BrandGetVm.fromModel(brand));
    }

    @GetMapping("/backoffice/brands/paging")
    public ResponseEntity<BrandListGetVm> getPagingBrands(
            @RequestParam(value = "pageNo", defaultValue = PageableConstant.DEFAULT_PAGE_NUMBER, required = false)
            int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageableConstant.DEFAULT_PAGE_SIZE, required = false)
            int pageSize
    ){
        return ResponseEntity.ok(brandService.getBrandList(pageNo, pageSize));
    }

    @PutMapping("/backoffice/brands/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = ErrorVm.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorVm.class)))})
    public ResponseEntity<Void> updateBrand(@PathVariable Integer id, @Valid @RequestBody final BrandPostVm brandPostVm) {
        brandService.update(brandPostVm, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/backoffice/brands/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = ErrorVm.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorVm.class)))})
    public ResponseEntity<Void> deleteBrand(@PathVariable Integer id) {
        brandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
