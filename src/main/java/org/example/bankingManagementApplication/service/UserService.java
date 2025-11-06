package org.example.bankingManagementApplication.service;

import org.example.bankingManagementApplication.dto.UserDto;
import org.example.bankingManagementApplication.entity.User;
import org.example.bankingManagementApplication.model.PaginationArgs;
import org.example.bankingManagementApplication.model.request.SignInRequest;
import org.example.bankingManagementApplication.model.request.SignUpRequest;
import org.example.bankingManagementApplication.model.request.UserUpdateRequest;
import org.example.bankingManagementApplication.model.response.LoginResponse;
import org.example.bankingManagementApplication.model.response.UserResponse;
import org.example.bankingManagementApplication.model.response.UserUpdateResponse;
import org.springframework.data.domain.Page;
import java.util.List;

public interface UserService {

    void signUp(SignUpRequest signUpRequest);

    List<UserResponse> getAllUsers();

    UserResponse getUser(Long id);

    UserResponse getUserByEmail(String email);

    List<UserResponse> getUserByUsername(String username);

    UserUpdateResponse updateUser(Long id, UserUpdateRequest userUpdateRequest);

    void deleteUser(Long id);

    Page<User> getAllUserPaginated(PaginationArgs paginationArgs);

    LoginResponse login(SignInRequest loginRequest);

    void saveUser(User superAdminUser);

    User findByEmail(String email);
}
