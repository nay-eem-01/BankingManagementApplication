package org.example.bankingManagementApplication.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class LoginResponse {
    private String accessToken;
    private String tokenType;
    private UserResponse userResponse;
}
