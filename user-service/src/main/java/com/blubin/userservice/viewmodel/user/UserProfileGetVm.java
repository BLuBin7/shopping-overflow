package com.blubin.userservice.viewmodel.user;

import com.blubin.userservice.model.Gender;
import com.blubin.userservice.model.UserProfile;

import java.time.LocalDate;
import java.util.UUID;

public record UserProfileGetVm(UUID id, String userName,
                               LocalDate dateOfBirth, Gender gender, String profileAvatars) {
    public static UserProfileGetVm fromModel(UserProfile userProfile) {
        return new UserProfileGetVm(
                userProfile.getId(),
                userProfile.getUserName(),
                userProfile.getDateOfBirth(),
                userProfile.getGender(),
                userProfile.getProfileAvatars()
        );
    }
}
