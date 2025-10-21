package org.example.bankingManagementApplication.service;

import org.example.bankingManagementApplication.entity.Privilege;
import org.example.bankingManagementApplication.entity.Role;
import org.example.bankingManagementApplication.entity.User;
import org.example.bankingManagementApplication.enums.RoleType;

import java.util.List;

public interface RoleService {


    Role findByRoleName(String userRole);

    void saveRole(Role superAdminRole);

    void createRole(String userRole, RoleType roleType, Object o, List<Privilege> consumerPrivilege);
    List<Role> getUserRoles(User user);
}
