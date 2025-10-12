package org.example.firstproject.service;

import org.example.firstproject.entity.Role;

public interface RoleService {
    Role findByRoleName(String userRole);
}
