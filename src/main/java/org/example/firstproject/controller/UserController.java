package org.example.firstproject.controller;

import org.example.firstproject.model.Response;
import org.example.firstproject.model.request.SignUpRequest;
import org.example.firstproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<Response> createUser(SignUpRequest signUpRequest){
        userService.createNewUser(signUpRequest);
        return Response.getResponseEntity(true,"User created successfully");
    }
}
