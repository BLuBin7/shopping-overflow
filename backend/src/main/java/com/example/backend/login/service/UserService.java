package com.example.backend.login.service;

import com.example.backend.login.model.User;
import org.springframework.context.annotation.Bean;

/**
 * Created by Binh
 * Date : 6/23/2023 - 12:11 AM
 * Description :
 */
public interface UserService {
    public User saveUser(User user);
}
