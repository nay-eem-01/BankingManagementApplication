package org.example.bankingManagementApplication.serviceImpl;

import org.example.bankingManagementApplication.entity.Role;
import org.example.bankingManagementApplication.repository.RoleRepository;
import org.example.bankingManagementApplication.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByRoleName(String userRole) {
        return roleRepository.findByRoleName(userRole);
    }
}
