package com.example.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.net.http.HttpRequest;

/**
 * Created by Binh
 * Date : 7/17/2023 - 9:38 PM
 * Description :
 */
@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager (DataSource dataSource){
        final var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_id,pw ,active from members where user_id=? "
        );
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?"
        );

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
        configurer
                .requestMatchers(HttpMethod.GET,"api/users").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET,"/api/users/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.POST,"/api/users").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT,"/api/users").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE,"/api/users/**").hasRole("ADMIN"));
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }

}
