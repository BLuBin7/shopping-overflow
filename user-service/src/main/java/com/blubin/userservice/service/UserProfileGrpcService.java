package com.blubin.userservice.service;

import com.blubin.proto.service.UserProfileRequest;
import com.blubin.proto.service.UserProfileResponse;
import com.blubin.proto.service.UserProfileServiceGrpc;
import com.blubin.userservice.model.UserProfile;
import com.blubin.userservice.repository.UserProfileRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class UserProfileGrpcService extends UserProfileServiceGrpc.UserProfileServiceImplBase {

    @Autowired
    private final UserProfileRepository userProfileRepository;

    @Override
    public void createUserProfile(UserProfileRequest request, StreamObserver<UserProfileResponse> responseObserver) {
        UUID userId = UUID.fromString(request.getUserId());
        String email = request.getEmail();

        UserProfile userProfile = userProfileRepository.findByEmailAddress(email)
                .orElseGet(() -> {
                    UserProfile newProfile = new UserProfile();
                    newProfile.setUserName(request.getUserName());
                    newProfile.setProfileAvatars(request.getProfileAvatars());
                    newProfile.setEmailAddress(email);
                    newProfile.setUserId(userId);
                    return userProfileRepository.save(newProfile);
                });

        UserProfileResponse response = UserProfileResponse.newBuilder()
                .setUserId(userProfile.getId().toString())
                .setUserName(userProfile.getUserName())
                .setProfileAvatars(userProfile.getProfileAvatars())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
