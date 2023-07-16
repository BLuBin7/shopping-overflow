package com.example.backend.login.service;


import com.example.backend.login.model.User;
import com.example.backend.login.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * Created by Binh
 * Date : 7/17/2023 - 10:01 PM
 * Description :
 */
public class UserServiceimpl implements UserService {
    private userRepository userRepository;

    @Autowired
    public UserServiceimpl(userRepository userRepositor) {
        this.userRepository = userRepositor;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int theId) {
        Optional<User> result =  userRepository.findById(theId);

        User theUser = null;

        if (result.isPresent()) {
            theUser= result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theUser;
    }

    @Override
    public User save(User theUser) {
        return userRepository.save(theUser);
    }

    @Override
    public void deleteById(int theId) {
        userRepository.deleteById(theId);
    }
}
