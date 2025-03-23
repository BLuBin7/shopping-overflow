package com.blubin.identityservice.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AuthService {
    public String getJwtFromCookie(HttpServletRequest request, String cookieName) {
        if (request.getCookies() == null) return null;

        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .map(cookie -> cookie.getValue())
                .findFirst()
                .orElse(null);
    }

}
