package com.example.backend.login.repository;

import com.example.backend.login.model.User;
import org.springframework.boot.autoconfigure.data.ConditionalOnRepositoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Binh
 * Date : 7/14/2023 - 11:23 PM
 * Description :
 */
//@RepositoryRestResource(path = "login")
public interface userRepository extends JpaRepository<User,Integer> {
}
