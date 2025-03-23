package com.blubin.identityservice.model;

import com.blubin.identityservice.repository.SiteUserRepository;
import com.blubin.identityservice.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomOidcUserService extends OidcUserService {
    @Autowired
    private SiteUserRepository siteUserRepository;

    @Autowired
    private JwtUtils jwtUtils;

    public CustomOidcUserService(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        try {
            return processOidcUser(userRequest, oidcUser);
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
        }
    }

    private OidcUser processOidcUser(OidcUserRequest userRequest, OidcUser oidcUser) {
        OidcUserAuthority oidcUserAuthority = (OidcUserAuthority) oidcUser.getAuthorities()
                .stream()
                .filter(OidcUserAuthority.class::isInstance)
                .findFirst()
                .orElse(null);

        String avatarUrl = null;
        if (oidcUserAuthority != null) {
            OidcUserInfo userInfo = oidcUserAuthority.getUserInfo();
            if (userInfo != null) {
                avatarUrl = userInfo.getClaim("picture");
            } else {
                avatarUrl = oidcUserAuthority.getIdToken().getClaim("picture");
            }
        }

        String email = oidcUser.getAttribute("email");
        String name = oidcUser.getAttribute("name");


        Optional<SiteUser> userOptional = siteUserRepository.findByEmailAddress(email);
        SiteUser siteUser;

        if (userOptional.isPresent()) {
            siteUser = userOptional.get();
        } else {
            siteUser = new SiteUser();
            siteUser.setEmailAddress(email);
            siteUser.setUserName(name);
            siteUser.setRole(UserRole.USER);
            siteUser.setPassword("");
            siteUserRepository.save(siteUser);
        }

        String jwt = jwtUtils.generateJwtToken(siteUser);

        Map<String, Object> claims = new HashMap<>(oidcUser.getClaims());
        claims.put("token", jwt);
        return new DefaultOidcUser(oidcUser.getAuthorities(), oidcUserAuthority.getIdToken(), new OidcUserInfo(claims));
    }
}
