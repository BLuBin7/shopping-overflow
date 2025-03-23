package com.blubin.identityservice.controller;

import com.blubin.identityservice.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@RestController
public class SpringSSOController {

    private final AuthService authService;

    public SpringSSOController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/backoffice/identity")
    public String welcome() {
        return "Welcome to Identity Service";
    }

    @GetMapping("/api/v1/backoffice")
    public String backoffice() {
        return "Backoffice";
    }

    @GetMapping("/access-token")
    public ResponseEntity<Map<String, Object>> getAccessToken(Authentication authentication) {
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

    @GetMapping("/access-token-cookie")
    public ResponseEntity<Map<String, Object>> getAccessToken(HttpServletRequest request) {
        String jwt = authService.getJwtFromCookie(request, "SERVER_SESSION");

        if (jwt != null) {
            return ResponseEntity.ok(Map.of("access_token", jwt));
        }

        return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));
    }



}
