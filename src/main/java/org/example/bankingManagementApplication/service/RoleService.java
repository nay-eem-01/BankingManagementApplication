package org.example.bankingManagementApplication.service;

import org.example.bankingManagementApplication.entity.Role;

public interface RoleService {
    Role findByRoleName(String userRole);
}
