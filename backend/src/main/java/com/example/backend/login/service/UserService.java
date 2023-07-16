package com.example.backend.login.service;

import com.example.backend.login.model.User;

import java.util.List;

/**
 * Created by Binh
 * Date : 7/17/2023 - 10:01 PM
 * Description :
 */
public interface UserService {
    List<User> findAll();

    User findById(int theId);

    User save(User theUser);

    void deleteById(int theId);
}
