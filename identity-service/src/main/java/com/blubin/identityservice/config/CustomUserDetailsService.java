package com.blubin.identityservice.config;

import com.blubin.identityservice.model.SiteUser;
import com.blubin.identityservice.repository.SiteUserRepository;
import com.blubin.identityservice.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SiteUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SiteUser user = userRepository.findByEmailAddress(email)
                .orElseThrow(() -> new UsernameNotFoundException(Constants.ErrorCodes.USER_NOT_FOUND_WITH_EMAIL + email));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmailAddress())
                .password(user.getPassword())
                .authorities(user.getRole().toString())
                .build();
    }
}