package com.blubin.userservice.controller;

import com.blubin.userservice.model.SiteUser;
import com.blubin.userservice.service.SiteUserService;
import com.blubin.userservice.viewmodel.user.SiteUserRequestVM;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private SiteUserService siteUserService;

    @GetMapping
    public String hello() {
        return "hello";
    }

    @PostMapping("/register")
    public ResponseEntity<SiteUser> addUser(@Valid @RequestBody SiteUserRequestVM request) {
        SiteUser createdUser = siteUserService.addUser(request);
        return ResponseEntity.ok(createdUser);
    }
}
