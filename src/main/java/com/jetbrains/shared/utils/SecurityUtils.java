package com.jetbrains.shared.utils;

import com.jetbrains.gymusserverjava.auth.entities.User;
import com.jetbrains.gymusserverjava.auth.repositories.UserRepository;
import com.jetbrains.shared.exceptions.CustomExceptionHandler;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
    @Autowired
    private UserRepository userRepository;

    public boolean isOwner(int id) {
        if (id <= 0) {
            return false;
        }

        var userDetails = Helpers.INSTANCE.getUserDetails();

        return userRepository.findOneByUsername(userDetails.getUsername())
                .map(user -> user.getId() == id)
                .orElseThrow(() -> CustomExceptionHandler.forbidden("user forbidden to get this data"));

    }

}
