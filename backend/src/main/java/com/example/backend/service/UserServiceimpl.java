package com.example.backend.service;


import com.example.backend.Exception.AppException;
import com.example.backend.dto.ProfileDto;
import com.example.backend.dto.SignUpDto;
import com.example.backend.dto.UserDto;
import com.example.backend.mapper.UserMapper;
import com.example.backend.model.User;
import com.example.backend.repository.userRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;
/**
 * Created by Binh
 * Date : 7/17/2023 - 10:01 PM
 * Description :
 */

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceimpl implements UserService {
    private final userRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long theId) {
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
    public void deleteById(Long theId) {
        userRepository.deleteById(theId);
    }

    private User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
    }
    public ProfileDto getProfile(Long userId) {
        User user = getUser(userId);
        log.trace("Reading profile for user {}", userId);
        return userMapper.userToProfileDto(user);
    }

    public UserDto signUp(SignUpDto userDto) {
        Optional<User> optionalUser = userRepository.findByLogin(userDto.getLogin());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));

        User savedUser = userRepository.save(user);

        log.info("Creating new user {}", userDto.getLogin());

        return userMapper.toUserDto(savedUser);
    }
}
