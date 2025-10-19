package org.example.bankingManagementApplication.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
