package org.example.firstproject.service;

import org.example.firstproject.model.request.SignUpRequest;

public interface UserService {
    public void createNewUser(SignUpRequest signUpRequest);
}
