package com.blubin.userservice.viewmodel.user;

import com.blubin.userservice.model.Gender;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record UserProfilePutVm(
        @NotNull UUID id,
        @NotNull @Size(max = 100) String firstName,
        @NotNull @Size(max = 100) String lastName,
        LocalDate dateOfBirth,
        @NotNull Gender gender,
        String profileAvatars,
        @NotNull UUID userId
) {}
