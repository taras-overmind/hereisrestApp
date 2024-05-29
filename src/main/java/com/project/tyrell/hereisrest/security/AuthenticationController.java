package com.project.tyrell.hereisrest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    private static final String STATIC_USERNAME = "admin";
    private static final String STATIC_PASSWORD = "password";

    @PostMapping("/authenticate")
    public String createAuthenticationToken(@RequestBody AuthenticationRequestBean authenticationRequest) throws Exception {

        if (!STATIC_USERNAME.equals(authenticationRequest.getUsername()) ||
                !STATIC_PASSWORD.equals(authenticationRequest.getPassword())) {
            throw new Exception("Incorrect username or password");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        return jwtUtil.generateToken(userDetails.getUsername());
    }
}


