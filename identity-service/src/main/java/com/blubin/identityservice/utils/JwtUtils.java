package com.blubin.identityservice.utils;

import com.blubin.identityservice.model.RefreshToken;
import com.blubin.identityservice.model.SiteUser;
import com.blubin.identityservice.repository.RefreshTokenRepository;
import com.blubin.identityservice.service.RefreshTokenService;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration:3600000}")
    private long jwtExpirationMs;

    private Key signingKey;

    @PostConstruct
    public void init() {
        if (jwtSecret == null || jwtSecret.isEmpty()) {
            throw new IllegalArgumentException(Constants.ErrorCodes.JWT_SECRET_NOT_EMPTY);
        }
//        signingKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    /**
     * Generates a JWT token for the authenticated user.
     *
     * @param userDetails Authenticated user details
     * @return JWT token as a string
     */
    public String generateJwtToken(UserDetails userDetails) {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        String roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
                .setHeaderParam("typ", "JWT")
                .setSubject(((SiteUser) userDetails).getEmailAddress())
                .setId(UUID.randomUUID().toString())
                .claim("roles", roles)
                .claim("userId", ((SiteUser) userDetails).getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(signingKey,SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extracts the email (subject) from the JWT token.
     *
     * @param token JWT token
     * @return Email extracted from the token
     */
    public String getEmailFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Validates the JWT token.
     *
     * @param authToken JWT token
     * @return True if valid, false otherwise
     */
//    public boolean validateJwtToken(String authToken) {
//        try {
//            Jwts.parserBuilder()
//                    .setSigningKey(signingKey)
//                    .build()
//                    .parseClaimsJws(authToken);
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            return false;
//        }
//    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(authToken)
                    .getBody();

            return true;

        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }

}