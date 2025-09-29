package org.example.firstproject.service;

import org.example.firstproject.entity.User;
import org.example.firstproject.model.response.UserResponse;

import java.util.List;

public interface UserServiceRestClient {
    List<UserResponse> getAllUser();
    UserResponse getUserById(Long id);
}
