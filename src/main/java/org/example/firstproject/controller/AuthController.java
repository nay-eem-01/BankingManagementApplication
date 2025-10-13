package org.example.firstproject.controller;

import jakarta.validation.Valid;
import org.example.firstproject.model.request.SignInRequest;
import org.example.firstproject.model.request.SignUpRequest;
import org.example.firstproject.model.response.HttpResponse;
import org.example.firstproject.model.response.LoginResponse;
import org.example.firstproject.service.UserService;
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
    public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody SignInRequest loginRequest){
        LoginResponse loginResponse =  userService.login(loginRequest);
        return new ResponseEntity<>(loginResponse,HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<HttpResponse> createUser(@Valid @RequestBody SignUpRequest signUpRequest){
        userService.createNewUser(signUpRequest);
        return HttpResponse.getResponseEntity(true,"User created successfully");
    }


}
