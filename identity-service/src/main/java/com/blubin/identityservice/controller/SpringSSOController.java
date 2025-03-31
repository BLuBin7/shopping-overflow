package com.blubin.identityservice.controller;

import com.blubin.identityservice.model.SiteUser;
import com.blubin.identityservice.service.RefreshTokenService;
import com.blubin.identityservice.service.SiteUserService;
import com.blubin.identityservice.utils.Constants;
import com.blubin.identityservice.utils.JwtUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
public class SpringSSOController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private SiteUserService siteUserService;


    @GetMapping("/backoffice/identity")
    public String welcome() {
        return "Welcome to Identity Service";
    }

    @GetMapping("/api/v1/backoffice")
    public String backoffice() {
        return "Backoffice";
    }

    @GetMapping("/access-token-from-oidc")
    public ResponseEntity<Map<String, Object>> getAccessTokenOidc(Authentication authentication) {
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User oauthUser = oauthToken.getPrincipal();

            if (oauthUser instanceof OidcUser oidcUser) {
                String accessToken = oidcUser.getIdToken().getTokenValue();
                return ResponseEntity.ok(Map.of("access_token", accessToken));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Unauthorized"));
    }

    @GetMapping("/access-token")
    public ResponseEntity<Map<String, Object>> getAccessToken(HttpServletRequest request) {
        String accessToken = jwtUtils.parseJwt(request);

        if (accessToken != null) {
            return ResponseEntity.ok(Map.of("access_token", accessToken));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Unauthorized"));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@CookieValue(name = "SERVER_SESSION", required = false) String refreshToken) {
        if (refreshToken == null || !refreshTokenService.validateRefreshToken(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Constants.ErrorCodes.INVALID_REFRESH_TOKEN);
        }

        UUID userId = refreshTokenService.getUserId(refreshToken);
        SiteUser siteUser = siteUserService.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException(Constants.ErrorCodes.USER_NOT_FOUND));

        String newAccessToken = jwtUtils.generateJwtToken(siteUser);

        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }
}
