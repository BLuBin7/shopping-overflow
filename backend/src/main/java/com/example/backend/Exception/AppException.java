package com.example.backend.Exception;

import org.springframework.http.HttpStatus;

/**
 * Created by Binh
 * Date : 7/27/2023 - 11:38 PM
 * Description :
 */
public class AppException extends RuntimeException {

    private final HttpStatus status;

    public AppException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
