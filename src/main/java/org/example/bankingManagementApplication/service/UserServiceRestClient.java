package org.example.bankingManagementApplication.service;

import org.example.bankingManagementApplication.model.response.UserResponse;

import java.util.List;

public interface UserServiceRestClient {
    List<UserResponse> getAllUser();
    UserResponse getUserById(Long id);
}
