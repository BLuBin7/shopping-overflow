package com.blubin.userservice.service;

import com.blubin.userservice.model.Country;
import com.blubin.userservice.repository.CountryRepository;
import com.blubin.userservice.viewmodel.country.CountryRequestVM;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class CountryService extends CircuitBreakerFallbackHandler{

    @Autowired
    private CountryRepository countryRepository;

    public Country addCountry(CountryRequestVM request) {
        Country country = new Country();
        country.setCountryName(request.countryName());
        return countryRepository.save(country);
    }

    @CircuitBreaker(name = "countryServiceCircuitBreaker", fallbackMethod = "handleGetCountryByIdFallback")
    public Country getCountryById(Integer id) {
        return countryRepository.findById(id).orElseThrow(() -> new RuntimeException("Country not found"));
    }

    public Country handleGetCountryByIdFallback(Integer id, Throwable throwable) throws Throwable {
        log.error("Fallback triggered for getCountryById with ID: {}", id);
        handleBodilessFallback(throwable);
        return null;
    }
}
