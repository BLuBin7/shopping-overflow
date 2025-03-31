package com.blubin.identityservice.utils;

public final class Constants {
    public static final String[] AUTH_WHITELIST = {
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-ui/index.html",
            "/",
            "/login",
    };

    public final class ErrorCodes {
        public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
        public static final String USER_NOT_FOUND_WITH_EMAIL = "USER_NOT_FOUND_WITH_EMAIL";
        public static final String USER_ALREADY_EXISTS = "USER_ALREADY_EXISTS";
        public static final String USER_ALREADY_LOGIN = "USER_ALREADY_LOGIN";
        public static final String USER_ALREADY_EXISTS_EMAIL = "USER_ALREADY_EXISTS_EMAIL";
        public static final String NOT_AUTHENTICATED = "NOT_AUTHENTICATED";
        public static final String INVALID_REFRESH_TOKEN = "INVALID_REFRESH_TOKEN";
        public static final String JWT_SECRET_NOT_EMPTY = "JWT_SECRET_NOT_EMPTY";
    }

    public final class Config{
        public static final String EMAIL_ADMIN = "EMAIL_ADMIN";
    }
}
