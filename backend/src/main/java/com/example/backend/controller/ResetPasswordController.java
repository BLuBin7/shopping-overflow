package com.example.backend.controller;

import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created by Binh
 * Date : 7/31/2023 - 12:27 AM
 * Description :
 */
@RestController
public class ResetPasswordController {

    private final UserService userService;

    @Autowired
    public ResetPasswordController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping({"/resetPass"})
    public ResponseEntity<String> resetPassword(
            @RequestParam("email") String email,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("newPasswordAgain") String newPasswordAgain
    ) {
        String resetToken = generateResetToken();

        // Check if the new passwords match
        if (!newPassword.equals(newPasswordAgain)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("New passwords do not match");
        }

        // Call the service to reset the password
        try {
            userService.resetPassword(email, newPassword);
            return ResponseEntity.ok("Password reset successfully");
//        } catch (ExceptionInInitializerError e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
    private String generateResetToken() {
        return UUID.randomUUID().toString();
    }
}
