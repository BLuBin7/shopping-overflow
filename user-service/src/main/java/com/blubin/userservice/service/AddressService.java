package com.blubin.userservice.service;

import com.blubin.userservice.model.Address;
import com.blubin.userservice.model.Country;
import com.blubin.userservice.repository.AddressRepository;
import com.blubin.userservice.repository.CountryRepository;
import com.blubin.userservice.viewmodel.country.AddressRequestVM;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CountryRepository countryRepository;

    public Address createAddress(@Valid @RequestBody AddressRequestVM request) {
        Address address = new Address();
        address.setUnitNumber(request.unitNumber());
        address.setStreetNumber(request.streetNumber());
        address.setAddressLine1(request.addressLine1());
        address.setAddressLine2(request.addressLine2());
        address.setCity(request.city());
        address.setRegion(request.region());
        address.setPostalCode(request.postalCode());

        Country country = countryRepository.findById(request.countryId())
                .orElseThrow(() -> new RuntimeException("Country not found"));
        address.setCountry(country);

        return addressRepository.save(address);
    }
}
