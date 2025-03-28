package com.blubin.identityservice.controller;
import com.blubin.identityservice.model.SiteUser;
import com.blubin.identityservice.service.SiteUserService;
import com.blubin.identityservice.viewmodel.SiteUserRequestVM;
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
