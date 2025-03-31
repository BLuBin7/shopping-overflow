package com.blubin.identityservice.config;

import com.blubin.identityservice.model.CustomOidcUserService;
import com.blubin.identityservice.utils.Constants;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Autowired
    private OAuth2LogoutSuccessHandler oAuth2LogoutSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, CustomOidcUserService customOidcUserService) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
//                .cors(Customizer.withDefaults())
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("http://localhost:3000"));
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                    config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
                    config.setExposedHeaders(List.of("Authorization"));
                    config.setAllowCredentials(true);
                    return config;
                }))
                .headers(headers -> headers
                        .xssProtection(xss -> xss.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK))
                        .contentSecurityPolicy(cps -> cps.policyDirectives("default-src 'self';base-uri 'self';"
                                + "font-src 'self' https: data:;form-action 'self';"
                                + "frame-ancestors 'self';img-src 'self' data:;"
                                + "object-src 'none';script-src 'self';"
                                + "script-src-attr 'none';style-src 'self' https: 'unsafe-inline';"
                                + "upgrade-insecure-requests")
                        )
                )
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(Constants.AUTH_WHITELIST).permitAll()
                        .requestMatchers("/api/v1/**").hasAnyAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .oidcUserService(customOidcUserService)
                                .userAuthoritiesMapper(this.userAuthoritiesMapper()))
                                .successHandler(oAuth2LoginSuccessHandler)
//                        .defaultSuccessUrl("/users", true)
                        .failureUrl("/login?error")
                )
                // JWT-based resource server configuration
//                 .oauth2ResourceServer(oauth2 -> oauth2
//                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
//                )

                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout")
                        .logoutSuccessHandler(oAuth2LogoutSuccessHandler)
                        .permitAll()
                )

                .httpBasic(Customizer.withDefaults());

        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    private GrantedAuthoritiesMapper userAuthoritiesMapper() {
        return (authorities -> {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

            authorities.forEach(authority -> {
                if (OidcUserAuthority.class.isInstance(authority)) {
                    OidcUserAuthority oidcUserAuthority = (OidcUserAuthority)authority;

                    OidcIdToken idToken = oidcUserAuthority.getIdToken();
                    OidcUserInfo userInfo = oidcUserAuthority.getUserInfo();
                    String email = idToken.getEmail();

                    if (email != null && email.endsWith("@gmail.com")) {
                        mappedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
                    } else {
                        mappedAuthorities.add(new SimpleGrantedAuthority("USER"));
                    }


                } else if (OAuth2UserAuthority.class.isInstance(authority)) {
                    OAuth2UserAuthority oauth2UserAuthority = (OAuth2UserAuthority)authority;

                    Map<String, Object> userAttributes = oauth2UserAuthority.getAttributes();
                }
            });

            return mappedAuthorities;
        });
    }

    /**
     * Configures how JWT tokens are converted to authentication objects.
     *
     * @return JwtAuthenticationConverter
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        authoritiesConverter.setAuthorityPrefix("ROLE_");
        authoritiesConverter.setAuthoritiesClaimName("roles");

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);
        return converter;
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
//        Dotenv dotenv = Dotenv.configure()
//                .directory("./")
//                .load();
        Dotenv dotenv = Dotenv.configure().
                directory(System.getProperty("user.dir")).load();
        return new InMemoryClientRegistrationRepository(
                this.googleClientRegistration(
                        dotenv.get("GOOGLE_CLIENT_ID"),
                        dotenv.get("GOOGLE_CLIENT_SECRET")
                ));
    }

    private ClientRegistration googleClientRegistration(String google_client_id, String google_client_secret) {
        return ClientRegistration.withRegistrationId("google")
                .clientId(google_client_id)
                .clientSecret(google_client_secret)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                .scope("openid", "profile", "email", "address", "phone")
                .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
                .tokenUri("https://www.googleapis.com/oauth2/v4/token")
                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                .userNameAttributeName(IdTokenClaimNames.SUB)
                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
                .clientName("Google")
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
