package com.blubin.productservice.viewmodel.colour;

import com.blubin.productservice.model.Colour;

import java.util.UUID;

public record ColourPostVm(UUID id, String colourName, String hexCode) {
    public Colour toModel() {
        Colour colour = new Colour();
        colour.setId(id);
        colour.setColourName(colourName);
        colour.setHexCode(hexCode);
        return colour;
    }
}
