//package com.example.backend.configuration.JwtOldVersion;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//
////@Configuration
////@EnableWebSecurity
////@EnableGlobalMethodSecurity(prePostEnabled = true)
////public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
////
////    @Autowired
////    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
////
////    @Autowired
////    private JwtRequestFilter jwtRequestFilter;
////
////    @Autowired
////    private UserDetailsService jwtService;
////
////    @Bean
////    @Override
////    public AuthenticationManager authenticationManagerBean() throws Exception {
////        return super.authenticationManagerBean();
////    }
////
////    @Override
////    protected void configure(HttpSecurity httpSecurity) throws Exception {
////        httpSecurity.cors();
////        httpSecurity.csrf().disable()
////                .authorizeRequests().antMatchers("/authenticate", "/registerNewUser", "/getAllProducts", "/getProductDetailsById/{productId}","/forgo","/resetPass").permitAll()
////                .antMatchers(HttpHeaders.ALLOW).permitAll()
////                .anyRequest().authenticated()
////                .and()
////                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
////                .and()
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////        ;
////
////        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
////        authenticationManagerBuilder.userDetailsService(jwtService).passwordEncoder(passwordEncoder());
////    }
////}
//
////@Configuration
//@EnableWebSecurity
//public class WebSecurityConfiguration   {
////    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
////        httpSecurity.csrf().disable()
////                .authorizeRequests().antMatchers("/authenticate", "/registerNewUser", "/getAllProducts", "/getProductDetailsById/{productId}","/forgo","/resetPass").permitAll()
////                .antMatchers(HttpHeaders.ALLOW).permitAll()
////                .anyRequest().authenticated()
////                .and()
////                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
////                .and()
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////        ;
//
//
////        httpSecurity
////                .csrf(csrf->csrf.disable())
////                .authorizeHttpRequests(auth -> {
////                    auth.requestMatchers(HttpHeaders.ALLOW).permitAll()
////                            .requestMatchers("/authenticate", "/registerNewUser", "/getAllProducts", "/getProductDetailsById/{productId}","/forgo","/resetPass").permitAll()
////
//    }
//}
