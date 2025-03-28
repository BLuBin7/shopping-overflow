package com.blubin.userservice.repository;

import com.blubin.userservice.model.UserProfile;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {
    Optional<UserProfile> findByUserId(@NotNull UUID userId);

    Optional<UserProfile> findByEmailAddress(@Size(max = 100) @NotNull String emailAddress);
}
