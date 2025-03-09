package com.blubin.userservice.controller;
import com.blubin.userservice.model.Country;
import com.blubin.userservice.service.CountryService;
import com.blubin.userservice.viewmodel.country.CountryRequestVM;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping
    public String HelloWorld() {
        return "Hello World";
    }

    @PostMapping("/add")
    public ResponseEntity<Country> addCountry(@RequestBody @Validated CountryRequestVM countryRequestVM ) {
        Country country = countryService.addCountry(countryRequestVM);
        return ResponseEntity.ok(country);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountry(@PathVariable Integer id) {
        Country country = countryService.getCountryById(id);
        if (country == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(country);
    }

}