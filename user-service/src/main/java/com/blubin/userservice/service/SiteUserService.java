package com.blubin.userservice.service;

import com.blubin.userservice.model.SiteUser;
import com.blubin.userservice.repository.SiteUserRepository;
import com.blubin.userservice.viewmodel.user.SiteUserRequestVM;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SiteUserService {

    @Autowired
    private SiteUserRepository siteUserRepository;

//    private PasswordEncoder passwordEncoder;

    public SiteUser addUser(SiteUserRequestVM request) {
        SiteUser user = new SiteUser();
        user.setEmailAddress(request.emailAddress());
        user.setPhoneNumber(request.phoneNumber());
//        user.setPassword(passwordEncoder.encode(request.password()));
        user.setPassword(request.password());

        return siteUserRepository.save(user);
    }
}