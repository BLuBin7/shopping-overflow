package com.blubin.productservice.controller;

import com.blubin.productservice.model.Colour;
import com.blubin.productservice.repository.ColourRepository;
import com.blubin.productservice.service.ColourService;
import com.blubin.productservice.utils.PageableConstant;
import com.blubin.productservice.viewmodel.colour.ColourGetVm;
import com.blubin.productservice.viewmodel.colour.ColourListGetVm;
import com.blubin.productservice.viewmodel.colour.ColourPostVm;
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

import java.util.UUID;

@RestController
public class ColourController {
    private static final Logger log = LoggerFactory.getLogger(ColourController.class);
    private final ColourRepository colourRepository;
    private final ColourService colourService;

    public ColourController(ColourRepository colourRepository, ColourService colourService) {
        this.colourRepository = colourRepository;
        this.colourService = colourService;
    }

    @PostMapping("/backoffice/colours")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(schema = @Schema(implementation = ColourGetVm.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorVm.class)))})
    public ResponseEntity<ColourGetVm> createColour(
            @Valid @RequestBody ColourPostVm colourPostVm,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Colour colour = colourService.create(colourPostVm);
        return ResponseEntity.created(uriComponentsBuilder.replacePath("/colour/{id}")
                        .buildAndExpand(colour.getId()).toUri())
                .body(ColourGetVm.fromModel(colour));
    }

    @GetMapping("/backoffice/colours/paging")
    public ResponseEntity<ColourListGetVm> getPagingColour(
            @RequestParam(value = "pageNo", defaultValue = PageableConstant.DEFAULT_PAGE_NUMBER, required = false)
            int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageableConstant.DEFAULT_PAGE_SIZE, required = false)
            int pageSize
    ){
        return ResponseEntity.ok(colourService.getColourList(pageNo, pageSize));
    }

    @PutMapping("/backoffice/colours/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = ErrorVm.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorVm.class)))})
    public ResponseEntity<Void> updateColour(@PathVariable UUID id, @Valid @RequestBody final ColourPostVm colourPostVm) {
        colourService.update(colourPostVm, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/backoffice/colours{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = ErrorVm.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorVm.class)))})
    public ResponseEntity<Void> deleteColour(@PathVariable UUID id) {
        colourService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
