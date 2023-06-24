package com.example.backend.login.controller;

import com.example.backend.login.model.User;
import com.example.backend.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Binh
 * Date : 6/23/2023 - 12:15 AM
 * Description :
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/add")
    public String add(@RequestBody User user){
        userService.saveUser(user);
        return "New user is added";
    }

    @GetMapping("/getall")
    public List<User> getAll(){
        return userService.getalluser();
    }
}
