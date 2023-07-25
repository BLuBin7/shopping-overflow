package com.example.backend.Exception;

/**
 * Created by Binh
 * Date : 7/12/2023 - 10:25 PM
 * Description :
 */
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
