package com.blubin.identityservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SpringSSOController {

    @GetMapping("/backoffice/identity")
    public String welcome() {
        return "Welcome to Identity Service";
    }

    @GetMapping("/api/v1/backoffice")
    public String backoffice() {
        return "Backoffice";
    }

    @GetMapping("/api/auth/token")
    public ResponseEntity<?> getToken(Authentication authentication) {
        if (authentication instanceof OAuth2AuthenticationToken oauth2Auth) {
            OAuth2User oauth2User = oauth2Auth.getPrincipal();
            Map<String, Object> attributes = oauth2User.getAttributes();
            String accessToken = (String) attributes.get("access_token");

            if (accessToken != null) {
                return ResponseEntity.ok(Map.of("access_token", accessToken));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No token available");
    }

}
