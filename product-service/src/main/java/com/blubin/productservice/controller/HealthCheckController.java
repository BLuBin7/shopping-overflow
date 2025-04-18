package com.blubin.productservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health-check")
public class HealthCheckController {
    @GetMapping
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Product Service is running");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> healthCheckForAdmin() {
        return ResponseEntity.ok("Hi Admin, welcome to Product Service");
    }
}
