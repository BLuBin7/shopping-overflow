package com.example.backend.repository;

import com.example.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Binh
 * Date : 7/14/2023 - 11:23 PM
 * Description :
 */
//@RepositoryRestResource(path = "login")
public interface userRepository extends JpaRepository<User,Long> {
    Optional<User> findByLogin(String login);
}
