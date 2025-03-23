package com.blubin.identityservice.config;

import com.blubin.identityservice.model.SiteUser;
import com.blubin.identityservice.utils.JwtUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtUtils jwtUtils;

    public OAuth2LoginSuccessHandler(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();

        String email = oidcUser.getEmail();
        String name = oidcUser.getAttribute("name");

        SiteUser siteUser = new SiteUser();
        siteUser.setEmailAddress(email);
        siteUser.setUserName(name);

        String token = jwtUtils.generateJwtToken(siteUser);

        Cookie cookie = new Cookie("SERVER_SESSION", URLEncoder.encode(token, StandardCharsets.UTF_8));
//        XSS
        cookie.setHttpOnly(true);
//        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60);
//        CSRF
        cookie.setAttribute("SameSite", "Lax");
        response.addCookie(cookie);

        response.sendRedirect("/users");
    }
}