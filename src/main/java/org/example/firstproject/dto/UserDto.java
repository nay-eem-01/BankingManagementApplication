package org.example.firstproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    @NotBlank(message = "Name can't be blank")
    private String name;
    @NotBlank(message = "Password can't be blank")
    @Size(min = 4, max = 8, message = "Password should be more than contain minimum 4 and maximum 8 characters")
    private String password;
    @NotBlank
    @Email(message = "Invalid email format")
    private String email;
}

