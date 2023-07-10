package com.example.backend.login.repository;

import com.example.backend.login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Binh
 * Date : 6/22/2023 - 10:32 PM
 * Description :
 */

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
