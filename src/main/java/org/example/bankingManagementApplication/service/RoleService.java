package org.example.bankingManagementApplication.service;

import org.example.bankingManagementApplication.entity.Role;
import org.example.bankingManagementApplication.entity.User;
import org.example.bankingManagementApplication.model.request.RoleCreateRequest;
import org.example.bankingManagementApplication.model.request.RoleUpdateRequest;

import java.util.List;

public interface RoleService {


    Role findByRoleName(String roleName);

    void saveRole(Role role);

    void createRole(RoleCreateRequest roleCreateRequest);

    List<Role> getUserRoles(User user);

    List<String> getRoleType();

    Role updateRole(RoleUpdateRequest roleUpdateRequest);

    void deleteRole(Long roleId);
}
