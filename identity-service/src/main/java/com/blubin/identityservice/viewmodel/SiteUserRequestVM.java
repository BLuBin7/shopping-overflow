package com.blubin.identityservice.viewmodel;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize
public record SiteUserRequestVM(
        @NotBlank(message = "Email address must not be blank")
        @Size(max = 255, message = "Email address must not exceed 255 characters")
        String emailAddress,

        @Size(max = 20, message = "Phone number must not exceed 20 characters")
        String phoneNumber,

        @NotBlank(message = "Password must not be blank")
        @Size(max = 255, message = "Password must not exceed 255 characters")
        String password
) {}