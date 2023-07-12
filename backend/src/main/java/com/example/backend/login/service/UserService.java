package com.example.backend.login.service;

import com.example.backend.login.model.User;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * Created by Binh
 * Date : 6/23/2023 - 12:11 AM
 * Description :
 */
public interface UserService {
    void save(User user);
    User findbyId(Integer id);
    List<User> findAll();
    List<User> findByUserName(String thelastname);
    void Update(User user,String value);
    void deleteByID(int id);
}
