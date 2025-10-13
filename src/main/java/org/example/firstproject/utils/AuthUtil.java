package org.example.firstproject.utils;

import lombok.extern.slf4j.Slf4j;
import org.example.firstproject.entity.User;
import org.example.firstproject.exceptionHandler.ResourceNotFoundException;
import org.example.firstproject.repository.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthUtil {

    private final UserRepository userRepository;

    public AuthUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getLoggedInUser (){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Email from authentication: {}",authentication.getName());
        return userRepository.findTopByEmail(authentication.getName()).orElseThrow(()-> new ResourceNotFoundException("User not found"));
    }
}
