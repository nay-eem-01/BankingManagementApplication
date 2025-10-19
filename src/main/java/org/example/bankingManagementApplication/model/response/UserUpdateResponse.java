package org.example.bankingManagementApplication.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserUpdateResponse {
    private String name;
    private String email;
}
