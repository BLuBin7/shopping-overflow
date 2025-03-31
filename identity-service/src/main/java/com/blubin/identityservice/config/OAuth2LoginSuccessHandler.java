package com.blubin.identityservice.config;

import com.blubin.identityservice.model.SiteUser;
import com.blubin.identityservice.repository.SiteUserRepository;
import com.blubin.identityservice.service.GrpcUserService;
import com.blubin.identityservice.service.RefreshTokenService;
import com.blubin.identityservice.utils.JwtUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private GrpcUserService grpcUserService;

    @Autowired
    private SiteUserRepository siteUserRepository;

    @Autowired
    private RefreshTokenService refreshTokenService;

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

        SiteUser siteUser = siteUserRepository.findByEmailAddress(email)
                .orElseGet(() -> {
                    SiteUser newUser = new SiteUser();
                    newUser.setEmailAddress(email);
                    newUser.setUserName(name);
                    return siteUserRepository.save(newUser);
                });

        String token = jwtUtils.generateJwtToken(siteUser);
        String refreshToken = refreshTokenService.createRefreshToken(siteUser.getId());
        System.out.println(token);
        System.out.println(refreshToken);
//       Save JWT to Header
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Authorization", "Bearer " + token);

//       Save JWT to Cookie
        Cookie cookie = new Cookie("SERVER_SESSION", URLEncoder.encode(refreshToken, StandardCharsets.UTF_8));
//        XSS
//        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60);
//        CSRF
        cookie.setAttribute("SameSite", "Lax");
        response.addCookie(cookie);

        String avatarUrl = oidcUser.getAttribute("picture");

        grpcUserService.createUserProfile(siteUser, avatarUrl, name);

        response.sendRedirect("/users");
    }
}