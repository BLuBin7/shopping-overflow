package com.example.backend.service;


import com.example.backend.dao.RoleDao;
import com.example.backend.dao.SellerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Binh
 * Date : 7/30/2023 - 2:48 PM
 * Description :
 */
@Service
public class SellerService {
    @Autowired
    private SellerDao sellerDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public void initRoleAndUser(){

    }
}
