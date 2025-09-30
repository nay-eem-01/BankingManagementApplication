package org.example.firstproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.example.firstproject.constatnt.AppTables;
import org.example.firstproject.model.AuditModel;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = AppTables.USER_NAME)
public class User extends AuditModel<String> {
    @NotBlank(message = "Name can't be blank")
    private String name;
    @NotBlank(message = "Password can't be blank")
    @Size(min = 4, max = 8,message = "Password should be more than contain minimum 4 and maximum 8 characters")
    private String password;
    @NotBlank
    @Email(message = "Invalid email format")
    private String email;

}

