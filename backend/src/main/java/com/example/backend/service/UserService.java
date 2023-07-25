package com.example.backend.service;

import com.example.backend.model.User;

import java.util.List;

/**
 * Created by Binh
 * Date : 7/17/2023 - 10:01 PM
 * Description :
 */
public interface UserService {
    List<User> findAll();

    User findById(Long theId);

    User save(User theUser);

    void deleteById(Long theId);
}
