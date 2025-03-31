package com.blubin.identityservice.service;

import com.blubin.identityservice.model.SiteUser;
import com.blubin.identityservice.repository.SiteUserRepository;
import com.blubin.identityservice.utils.Constants;
import com.blubin.identityservice.viewmodel.SiteUserRequestVM;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SiteUserService {

    @Autowired
    private SiteUserRepository siteUserRepository;

    private PasswordEncoder passwordEncoder;

    public SiteUser addUser(SiteUserRequestVM request) {
        SiteUser user = new SiteUser();
        user.setEmailAddress(request.emailAddress());
        user.setPhoneNumber(request.phoneNumber());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setPassword(request.password());

        return siteUserRepository.save(user);
    }

    public SiteUser findByEmailAddress(String email) {
        return siteUserRepository.findByEmailAddress(email)
                .orElseThrow(() -> new UsernameNotFoundException(Constants.ErrorCodes.USER_NOT_FOUND));
    }

    public Optional<SiteUser> findByUserId(UUID userId) {
        return siteUserRepository.findById(userId);
    }
}