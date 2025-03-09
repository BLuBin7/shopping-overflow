package com.blubin.identityservice.repository;

import com.blubin.identityservice.model.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SiteUserRepository extends JpaRepository<SiteUser, UUID> {
    boolean existsByEmailAddress(String emailAddress);
    Optional<SiteUser> findByEmailAddress(String emailAddress);
}