package com.example.backend.controller;


import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Resource(name = "redisTemplate")
    private HashOperations<String,String,User> hashOperations;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        hashOperations.put("User",user.getUserName(),user);
        return userService.registerNewUser(user);
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/getProfile/{userName}"})
    public User getProfile(@PathVariable("userName") String userName){
        return userService.getProfileUserById(userName);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }
}
