package org.example.firstproject.service;

import org.example.firstproject.dto.UserDto;
import org.example.firstproject.entity.User;
import org.example.firstproject.model.PaginationArgs;
import org.example.firstproject.model.request.SignInRequest;
import org.example.firstproject.model.request.SignUpRequest;
import org.example.firstproject.model.response.LoginResponse;
import org.example.firstproject.model.response.UserResponse;
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
