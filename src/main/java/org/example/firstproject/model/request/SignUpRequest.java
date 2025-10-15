package org.example.firstproject.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@ToString
public class SignUpRequest {

    @NotBlank(message = "Name can't be blank")
    private String fullName;
    @NotBlank(message = "Password can't be blank")
    @Size(min = 4, message = "Password should  contain minimum 4 characters")
    private String password;
    @NotBlank
    @Email(message = "Invalid email format")
    private String email;
}
