package com.blubin.userservice.viewmodel.country;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize
public record CountryRequestVM (
        @NotBlank(message = "Country name must not be blank")
        @Size(max = 255, message = "Country name must not exceed 255 characters")
        String countryName
) {}
