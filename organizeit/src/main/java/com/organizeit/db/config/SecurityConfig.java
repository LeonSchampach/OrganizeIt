package com.organizeit.db.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This class configures security-related components and beans for the SmartLibrary application.
 * It focuses on enabling strong password encoding using BCrypt.
 */
@Configuration
public class SecurityConfig {

    /**
     * Creates a bean of type BCryptPasswordEncoder, which will be used throughout the
     * application for secure password hashing and matching.
     *
     * @return A BCryptPasswordEncoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

