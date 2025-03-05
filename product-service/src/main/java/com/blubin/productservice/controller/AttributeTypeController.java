package com.blubin.productservice.controller;

import com.blubin.productservice.model.AttributeType;
import com.blubin.productservice.repository.AttributeTypeRepository;
import com.blubin.productservice.service.AttributeTypeService;
import com.blubin.productservice.viewmodel.attribute.attributetype.AttributeTypeGetVm;
import com.blubin.productservice.viewmodel.attribute.attributetype.AttributeTypePostVm;
import com.blubin.productservice.viewmodel.error.ErrorVm;
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
public class AttributeTypeController {
    private final AttributeTypeService attributeTypeService;

    private final AttributeTypeRepository attributeTypeRepository;

    public AttributeTypeController(AttributeTypeRepository attributeTypeRepository, AttributeTypeService attributeTypeService) {
        this.attributeTypeRepository = attributeTypeRepository;
        this.attributeTypeService = attributeTypeService;
    }

    @PostMapping("backoffice/attribute_type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(schema = @Schema(implementation = AttributeTypeGetVm.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorVm.class)))})
    public ResponseEntity<AttributeTypeGetVm> createattributeType(
            @Valid @RequestBody AttributeTypePostVm attributeTypePostVm,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        AttributeType attributeType = attributeTypeService.create(attributeTypePostVm);
        AttributeTypeGetVm attributeTypeGetVm = AttributeTypeGetVm.fromModel(attributeType);
        return ResponseEntity.created(uriComponentsBuilder.replacePath("/atttribite_type/{id}")
                        .buildAndExpand(attributeType.getId()).toUri())
                .body(attributeTypeGetVm);
    }
}
