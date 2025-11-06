package org.example.bankingManagementApplication.model.request;

import lombok.*;
import org.example.bankingManagementApplication.enums.RoleType;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoleCreateRequest {

    private String roleName;

    private long[] privilegeId;

    private RoleType roleType;

}
