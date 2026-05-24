package com.jetbrains.gymusserverjava.auth.Impl;

import com.jetbrains.gymusserverjava.auth.repositories.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public @NonNull UserDetails loadUserByUsername(
            @NonNull String username
    ) throws UsernameNotFoundException {
        var foundedUser = userRepository.findByUsername(username)
                                        .orElseThrow(() -> new UsernameNotFoundException(
                                                "User not found"));
        return User
                .withUsername(foundedUser.getUsername())
                .username(foundedUser.getUsername())
                .password(foundedUser.getPassword())
                .roles(foundedUser.getRole())
                .build();
    }

}
