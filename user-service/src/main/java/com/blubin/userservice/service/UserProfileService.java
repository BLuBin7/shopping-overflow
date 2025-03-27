package com.blubin.userservice.service;

import com.blubin.userservice.model.UserProfile;
import com.blubin.userservice.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    public void createUserProfile(UUID userId, String userName) {
        UserProfile profile = new UserProfile();
        profile.setUserId(userId);
        profile.setUserName(userName);
        userProfileRepository.save(profile);
    }
}
