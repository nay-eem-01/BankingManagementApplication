package org.example.firstproject.service;

import org.example.firstproject.dto.UserDto;
import org.example.firstproject.entity.User;
import org.example.firstproject.model.request.SignUpRequest;
import org.example.firstproject.model.response.UserResponse;

import java.util.List;

public interface UserService {
     void createNewUser(SignUpRequest signUpRequest);

    List<UserResponse> getAllUsers();

    UserResponse getUser(Long id);

    UserResponse getUserByEmail(String email);

    List<UserResponse> getUserByUsername(String username);

    UserDto updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);

    List<String> getAllUniqueName();

    List<User> getUserNameStartsWith(String keyword);
    List<User> getUserNameEndsWith(String keyword);

    List<User> getUserNameContains(String keyword);

    List<User> getUserByCriteria(String name);
}
