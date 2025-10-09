package org.example.firstproject.security.service;

import org.example.firstproject.entity.User;
import org.example.firstproject.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User loggedUser = userRepository.findTopByName(username).orElseThrow(()-> new UsernameNotFoundException("User not found with username" + username));
        return CustomUserDetails.build(loggedUser);
    }
}
