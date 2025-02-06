package com.blubin.userservice.repository;

import com.blubin.userservice.model.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteUserRepository extends JpaRepository<SiteUser, Integer> {
    boolean existsByEmailAddress(String emailAddress);
}