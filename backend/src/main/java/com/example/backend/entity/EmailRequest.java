package com.example.backend.entity;

/**
 * Created by Binh
 * Date : 7/30/2023 - 9:58 PM
 * Description :
 */
public class EmailRequest {
    private String email;

    // Default constructor (required for deserialization)
    public EmailRequest() {
    }

    // Constructor with email parameter
    public EmailRequest(String email) {
        this.email = email;
    }

    // Getter and Setter for the email field
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

