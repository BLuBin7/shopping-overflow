package com.example.backend.login.dao;

import com.example.backend.login.model.User;

import java.util.List;

/**
 * Created by Binh
 * Date : 7/11/2023 - 10:20 PM
 * Description :
 */
public interface UserDAO {
    void save(User user);
    User findbyId(Integer id);
    List<User> findAll();
    List<User> findByUserName(String thelastname);
    void Update(User user,String value);
}
