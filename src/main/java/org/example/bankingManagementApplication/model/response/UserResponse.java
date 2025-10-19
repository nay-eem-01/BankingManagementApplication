package org.example.bankingManagementApplication.model.response;

import lombok.*;
import org.example.bankingManagementApplication.model.AuditModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponse extends AuditModel<String> {
    private String name;
    private String email;
}
