package com.blubin.userservice.controller;

import com.blubin.userservice.model.Address;
import com.blubin.userservice.model.Country;
import com.blubin.userservice.service.AddressService;
import com.blubin.userservice.viewmodel.country.AddressRequestVM;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping
    public String Test(){
        return "test api address";
    }

    @PostMapping("/new-address")
    public ResponseEntity<Address> createAddress(@Valid @RequestBody AddressRequestVM request) {
        Address address = addressService.createAddress(request);
        return ResponseEntity.ok(address);
    }
}
