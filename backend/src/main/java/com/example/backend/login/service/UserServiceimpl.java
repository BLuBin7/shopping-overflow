package com.example.backend.login.service;

import com.example.backend.login.dao.UserDAO;
import com.example.backend.login.model.User;
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
    private UserDAO userDAO;

    @Autowired
    public UserServiceimpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }


    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public User findbyId(Integer id) {
        return null;
    }

    @Override
    public List<User> findByUserName(String thelastname) {
        return null;
    }

    @Override
    public void Update(User user, String value) {

    }

    @Override
    public void deleteByID(int id) {
        userDAO.deleteByID(id);
    }
}
