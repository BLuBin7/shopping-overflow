package com.blubin.identityservice.utils;

import com.blubin.userservice.model.SiteUser;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    @Value("${jwt.secret:eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTc0MDkzOTAzNCwiaWF0IjoxNzQwOTM5MDM0fQ.RaE-tYEFCkPbFo-VKgz4YPHOh9RvKpCN40SpxoiCzms\n}")
    private String jwtSecret;

    @Value("${jwt.expiration:3600000}")
    private long jwtExpirationMs;

    private Key signingKey;

    @PostConstruct
    public void init() {
        if (jwtSecret == null || jwtSecret.isEmpty()) {
            throw new IllegalArgumentException("JWT secret must not be null or empty");
        }
        signingKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
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
                .setSubject(((SiteUser) userDetails).getEmailAddress())
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
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Log the exception or handle it as needed
            return false;
        }
    }
}