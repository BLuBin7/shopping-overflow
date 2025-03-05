package com.blubin.userservice.repository;

import com.blubin.userservice.model.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SiteUserRepository extends JpaRepository<SiteUser, Integer> {
    boolean existsByEmailAddress(String emailAddress);
    Optional<SiteUser> findByEmailAddress(String emailAddress);
}