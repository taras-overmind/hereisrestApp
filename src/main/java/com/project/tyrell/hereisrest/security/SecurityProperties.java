package com.project.tyrell.hereisrest.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.security")
@Data
public class SecurityProperties {

    private String username;
    private String password;
}
