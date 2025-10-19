package org.example.bankingManagementApplication.dto;

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
    @NotBlank
    @Email(message = "Invalid email format")
    private String email;
}

