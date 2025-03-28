package com.blubin.identityservice.service;

import com.blubin.identityservice.model.SiteUser;
import com.blubin.proto.service.UserProfileRequest;
import com.blubin.proto.service.UserProfileServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class GrpcUserService {
    @GrpcClient("user-service")
    private UserProfileServiceGrpc.UserProfileServiceBlockingStub userProfileStub;

    public void createUserProfile(SiteUser siteUser, String avatarUrl, String name) {
        UserProfileRequest request = UserProfileRequest.newBuilder()
                .setUserName(name)
                .setEmail(siteUser.getEmailAddress())
                .setUserId(siteUser.getId().toString())
                .setProfileAvatars(avatarUrl)
                .build();

        userProfileStub.createUserProfile(request);
    }
}
