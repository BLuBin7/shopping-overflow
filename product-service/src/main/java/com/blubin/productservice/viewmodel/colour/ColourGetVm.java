package com.blubin.productservice.viewmodel.colour;

import com.blubin.productservice.model.Colour;

import java.util.UUID;

public record ColourGetVm(UUID id, String colourName, String hexCode) {
    public static ColourGetVm fromModel(Colour colour) {
        return new ColourGetVm(colour.getId(), colour.getColourName(), colour.getHexCode());
    }
}
