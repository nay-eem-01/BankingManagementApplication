package org.example.bankingManagementApplication.controller;

import jakarta.validation.Valid;
import org.example.bankingManagementApplication.model.request.SignInRequest;
import org.example.bankingManagementApplication.model.request.SignUpRequest;
import org.example.bankingManagementApplication.model.response.HttpResponse;
import org.example.bankingManagementApplication.model.response.LoginResponse;
import org.example.bankingManagementApplication.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<HttpResponse> authenticateUser(@Valid @RequestBody SignInRequest loginRequest) {
        LoginResponse loginResponse = userService.login(loginRequest);
        return HttpResponse.getResponseEntity(HttpStatus.OK, "User logged in successfully", loginResponse, true);
    }

    @PostMapping("/signup")
    public ResponseEntity<HttpResponse> createUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        userService.signUp(signUpRequest);
        return HttpResponse.getResponseEntity(HttpStatus.CREATED, "User created successfully", true);
    }


}
