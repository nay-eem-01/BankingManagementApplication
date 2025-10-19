package org.example.bankingManagementApplication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.example.bankingManagementApplication.constatnt.AppTables;
import org.example.bankingManagementApplication.model.AuditModel;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = AppTables.USER_NAME)
public class User extends AuditModel<String> {

    @NotBlank(message = "Name can't be blank")
    @Column(name = AppTables.UserTable.NAME)
    private String fullName;

    @NotBlank(message = "Password can't be blank")
    @Size(min = 4,message = "Password should contain at least 4 character")
    @Column(name = AppTables.UserTable.PASSWORD)
    private String password;

    @NotBlank
    @Email(message = "Invalid email format")
    @Column(name = AppTables.UserTable.EMAIL)
    private String email;

    @ManyToMany(fetch =  FetchType.EAGER , cascade =  CascadeType.DETACH)
    @JoinTable(
            name = AppTables.USER_ROLE_NAME,
            joinColumns = @JoinColumn(name = AppTables.UserTable.USER_ID),
            inverseJoinColumns = @JoinColumn(name = AppTables.RoleTable.ROLE_ID)
    )
    private Set<Role> roles ;
}

