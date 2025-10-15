package org.example.firstproject.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class SignInRequest {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
