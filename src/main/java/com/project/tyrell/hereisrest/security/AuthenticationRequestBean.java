package com.project.tyrell.hereisrest.security;

import lombok.Data;

@Data
public class AuthenticationRequestBean {

    private String username;
    private String password;
}