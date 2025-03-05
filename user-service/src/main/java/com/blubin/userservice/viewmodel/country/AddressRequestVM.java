package com.blubin.userservice.viewmodel.country;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize
public record AddressRequestVM(
        @Size(max = 20, message = "Unit number must not exceed 20 characters")
        String unitNumber,

        @Size(max = 20, message = "Street number must not exceed 20 characters")
        String streetNumber,

        @NotBlank(message = "Address Line 1 must not be blank")
        @Size(max = 255, message = "Address Line 1 must not exceed 255 characters")
        String addressLine1,

        @Size(max = 255, message = "Address Line 2 must not exceed 255 characters")
        String addressLine2,

        @NotBlank(message = "City must not be blank")
        @Size(max = 100, message = "City must not exceed 100 characters")
        String city,

        @Size(max = 100, message = "Region must not exceed 100 characters")
        String region,

        @Size(max = 20, message = "Postal code must not exceed 20 characters")
        String postalCode,

        @NotNull(message = "Country ID must not be null")
        Integer countryId
) {}