package org.example.bankingManagementApplication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.bankingManagementApplication.config.CommonApiResponses;
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
@CommonApiResponses
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @Operation(summary = "Sign-in")
    @ApiResponse(content = @Content(schema = @Schema(implementation = HttpResponse.class)), responseCode = "200")
    @PostMapping("/login")
    public ResponseEntity<HttpResponse> authenticateUser(@Valid @RequestBody SignInRequest loginRequest) {
        LoginResponse loginResponse = userService.login(loginRequest);
        return HttpResponse.getResponseEntity(HttpStatus.OK, "User logged in successfully", loginResponse, true);
    }

    @Operation(summary = "Sign-up")
    @ApiResponse(content = @Content(schema = @Schema(implementation = HttpResponse.class)), responseCode = "200")
    @PostMapping("/signup")
    public ResponseEntity<HttpResponse> createUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        userService.signUp(signUpRequest);
        return HttpResponse.getResponseEntity(HttpStatus.CREATED, "User created successfully", true);
    }
}
