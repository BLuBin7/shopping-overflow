package com.blubin.identityservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSSOController {

    @GetMapping
    public String welcome() {
        return "Welcome to Identity Service";
    }
}
