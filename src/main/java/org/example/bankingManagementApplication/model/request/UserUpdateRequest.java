package org.example.bankingManagementApplication.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserUpdateRequest {
    @NotBlank(message = "Name can't be blank")
    private String fullName;
    @NotBlank
    @Email(message = "Invalid email format")
    private String email;
}
