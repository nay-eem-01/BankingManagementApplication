package org.example.bankingManagementApplication.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.bankingManagementApplication.entity.Role;
import org.example.bankingManagementApplication.repository.RoleRepository;
import org.example.bankingManagementApplication.service.RoleService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role findByRoleName(String userRole) {
        return roleRepository.findByRoleName(userRole);
    }
}
