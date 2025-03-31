package com.blubin.identityservice.repository;

import com.blubin.identityservice.model.RefreshToken;
import com.blubin.identityservice.model.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);

    void deleteByUser(SiteUser user);

    Optional<RefreshToken> findByUser(SiteUser user);
}

