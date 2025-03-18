package com.reyga.dev.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private String currentUsername;
    private String currentPassword;
    private String[] currentRoles;

    public void setUserCredentials(String username, String password, String... roles) {
        this.currentUsername = username;
        this.currentPassword = password;
        this.currentRoles = roles;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (currentUsername == null || currentUsername.isEmpty() || currentUsername.isBlank()) {
            throw new UsernameNotFoundException("User Credentials must be provided");
        }

        if (username.equals(currentUsername)) {
            return User.withUsername(username)
                    .password(new BCryptPasswordEncoder().encode(currentPassword))
                    .roles(currentRoles)
                    .build();
        }
        throw new UsernameNotFoundException("User not found");
    }
}
