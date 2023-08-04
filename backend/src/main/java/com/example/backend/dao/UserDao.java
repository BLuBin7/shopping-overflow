package com.example.backend.dao;

import com.example.backend.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, String> {
//    public User findByEmail(String email);
}
