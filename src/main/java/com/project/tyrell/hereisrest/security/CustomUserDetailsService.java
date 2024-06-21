package com.project.tyrell.hereisrest.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Value("${security.user.name}")
    private String username;

    @Value("${security.user.password}")
    private String password;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (!username.equals(s)) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.withUsername(username)
                .password(password)
                .authorities("ROLE_USER")
                .build();
    }

    boolean validateAuthenticationRequest(final AuthenticationRequest authRequest) {
        return username.equals(authRequest.getUsername()) && password.equals(authRequest.getPassword());
    }

}
