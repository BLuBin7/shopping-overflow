package com.blubin.identityservice.service;

import com.blubin.identityservice.model.RefreshToken;
import com.blubin.identityservice.model.SiteUser;
import com.blubin.identityservice.repository.RefreshTokenRepository;
import com.blubin.identityservice.repository.SiteUserRepository;
import com.blubin.identityservice.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private SiteUserRepository siteUserRepository;

    public String createRefreshToken(UUID userId) {
        SiteUser siteUser = siteUserRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(Constants.ErrorCodes.USER_NOT_FOUND));
        Optional<RefreshToken> existingToken = refreshTokenRepository.findByUser(siteUser);
        RefreshToken refreshToken;

        if (existingToken.isPresent()) {
            refreshToken = existingToken.get();
            refreshToken.setRevoked(false);
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setExpiryDate(LocalDateTime.now().plusDays(30));
        } else {
            refreshToken = new RefreshToken();
            refreshToken.setUser(siteUserRepository.findById(userId).orElseThrow());
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setExpiryDate(LocalDateTime.now().plusDays(30));
        }

        return refreshTokenRepository.save(refreshToken).getToken();
    }


    public boolean validateRefreshToken(String token) {
        return refreshTokenRepository.findByToken(token)
                .map(rt -> !rt.getExpiryDate().isBefore(LocalDateTime.now()) && !rt.getRevoked())
                .orElse(false);
    }

    public void revokeRefreshToken(String token) {
        refreshTokenRepository.findByToken(token).ifPresent(rt -> {
            rt.setRevoked(true);
            refreshTokenRepository.save(rt);
        });
    }

    public UUID getUserId(String refreshToken) {
        return refreshTokenRepository.findByToken(refreshToken)
                .filter(rt -> !rt.getExpiryDate().isBefore(LocalDateTime.now()) && !rt.getRevoked())
                .map(rt -> rt.getUser().getId())
                .orElseThrow(() -> new RuntimeException(Constants.ErrorCodes.INVALID_REFRESH_TOKEN));
    }

}
