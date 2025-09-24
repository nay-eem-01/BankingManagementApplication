package org.example.firstproject.serviceImpl;

import org.example.firstproject.entity.User;
import org.example.firstproject.model.request.SignUpRequest;
import org.example.firstproject.repository.UserRepository;
import org.example.firstproject.security.PasswordEncoder;
import org.example.firstproject.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void createNewUser(SignUpRequest signUpRequest) {
        User userExist = userRepository.findTopByEmail(signUpRequest.getEmail()).orElse(null);

        if (userExist != null){
            throw new RuntimeException("User with this email already exist");
        }
        String username = signUpRequest.getName();
        String email = signUpRequest.getEmail();
        String password = signUpRequest.getPassword();

        User signedInUser = new User();

        signedInUser.setEmail(email);
        signedInUser.setName(username);
        signedInUser.setPassword(password);

        userRepository.save(signedInUser);

    }
}
