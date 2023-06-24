package com.example.backend.login.service;

import com.example.backend.login.model.User;
import com.example.backend.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Binh
 * Date : 6/23/2023 - 12:12 AM
 * Description :
 */
@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getalluser() {
        return userRepository.findAll();
    }


}
