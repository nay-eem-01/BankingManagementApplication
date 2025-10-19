package org.example.bankingManagementApplication.service;

import org.example.bankingManagementApplication.dto.UserDto;
import org.example.bankingManagementApplication.entity.User;
import org.example.bankingManagementApplication.model.PaginationArgs;
import org.example.bankingManagementApplication.model.request.SignInRequest;
import org.example.bankingManagementApplication.model.request.SignUpRequest;
import org.example.bankingManagementApplication.model.response.LoginResponse;
import org.example.bankingManagementApplication.model.response.UserResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    void createNewUser(SignUpRequest signUpRequest);

    List<UserResponse> getAllUsers();

    UserResponse getUser(Long id);

    UserResponse getUserByEmail(String email);

    List<UserResponse> getUserByUsername(String username);

    UserDto updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);

    Page<User> getAllUserPaginated(PaginationArgs paginationArgs);

    LoginResponse login(SignInRequest loginRequest);


}
