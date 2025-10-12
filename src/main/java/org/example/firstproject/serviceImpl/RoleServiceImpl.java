package org.example.firstproject.serviceImpl;

import org.example.firstproject.entity.Role;
import org.example.firstproject.repository.RoleRepository;
import org.example.firstproject.service.RoleService;
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
