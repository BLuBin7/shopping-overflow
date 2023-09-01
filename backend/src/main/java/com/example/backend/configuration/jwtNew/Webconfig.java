//package com.example.backend.configuration.jwtNew;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
////@EnableWebSecurity
//public class Webconfig {
//
//        @Bean
//        public InMemoryUserDetailsManager userDetailsManager(){
//            UserDetails john = User.builder()
//                    .username("john")
//                    .password("{noop}test123")
//                    .roles("USER")
//                    .build();
//            return new InMemoryUserDetailsManager(john);
//        }
//
//        @Bean
//        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//            http.authorizeHttpRequests(configurer->
//                            configurer
//                                    .anyRequest().authenticated()
//                    )
//                    .formLogin(form ->
//                            form
//                                    .loginPage("http://localhost:3000/signin")
////                                    /registerNewUser
//                                    .loginProcessingUrl("authenticateTheUser")
//                                    .permitAll()
//                    );
//            return http.build();
//        }
//
//
//
//}
