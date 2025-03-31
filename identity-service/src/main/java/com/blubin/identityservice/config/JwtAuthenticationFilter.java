package com.blubin.identityservice.config;

import com.blubin.identityservice.service.RefreshTokenService;
import com.blubin.identityservice.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(CustomUserDetailsService userDetailsService, JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if(jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String email = jwtUtils.getEmailFromJwtToken(jwt);
                var userDetails = userDetailsService.loadUserByUsername(email);
                var authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("Authenticated user: " + email);
                String authHeader = request.getHeader("Authorization");
                System.out.println("Authorization Header: " + authHeader);
            }
        } catch(Exception e) {
            logger.error("Cannot set user authentication}");
        }

        filterChain.doFilter(request, response);
    }

//    get JWT from header
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if(headerAuth != null && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }

//    get JWT from header and cookie
//    private String parseJwt(HttpServletRequest request) {
//        String headerAuth = request.getHeader("Authorization");
//        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
//            return headerAuth.substring(7);
//        }
//
//        if (request.getCookies() != null) {
//            for (Cookie cookie : request.getCookies()) {
//                if ("SERVER_SESSION".equals(cookie.getName())) {
//                    return URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
//                }
//            }
//        }
//
//        return null;
//    }
}
