package com.epam.edu.module2securityapplications.service;

import com.epam.edu.module2securityapplications.configuration.Permissions;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

@Component
public class UserInitializer {

    public UserInitializer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private final PasswordEncoder passwordEncoder;

    public Set<UserDetails> initTestUsers() {
        return Set.of(
                User.builder()
                        .username("admin@gmail.com")
                        .password("admin_pass")
                        .passwordEncoder(passwordEncoder::encode)
                        .authorities(Permissions.VIEW_ADMIN.getAuthority())
                        .build(),
                User.builder()
                        .username("user@gmail.com")
                        .password("user_pass")
                        .passwordEncoder(passwordEncoder::encode)
                        .authorities(Permissions.VIEW_INFO.getAuthority())
                        .build(),
                User.builder()
                        .username("noname@gmail.com")
                        .password("noname_pass")
                        .passwordEncoder(passwordEncoder::encode)
                        .authorities(Collections.emptyList())
                        .build()
        );
    }
}
