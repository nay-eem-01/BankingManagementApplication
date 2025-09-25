package org.example.firstproject.model.response;

import lombok.*;
import org.example.firstproject.model.AuditModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse extends AuditModel<String> {
    private String name;
    private String email;
}
