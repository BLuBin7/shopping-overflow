package com.blubin.userservice.viewmodel.user;

import com.blubin.userservice.model.Gender;
import com.blubin.userservice.model.UserProfile;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record UserProfilePostVm(
        @NotNull @Size(max = 100) String userName,
        LocalDate dateOfBirth,
        @NotNull Gender gender,
        String profileAvatars,
        @NotNull UUID userId
) {
    public UserProfile toModel() {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserName(userName);
        userProfile.setDateOfBirth(dateOfBirth);
        userProfile.setGender(gender);
        userProfile.setProfileAvatars(profileAvatars);
        userProfile.setUserId(userId);
        return userProfile;
    }
}
