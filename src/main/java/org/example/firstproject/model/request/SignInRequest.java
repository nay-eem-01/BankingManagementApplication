package org.example.firstproject.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequest {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
