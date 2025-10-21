package org.example.bankingManagementApplication.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.bankingManagementApplication.entity.Privilege;
import org.example.bankingManagementApplication.entity.Role;
import org.example.bankingManagementApplication.entity.User;
import org.example.bankingManagementApplication.enums.RoleType;
import org.example.bankingManagementApplication.repository.RoleRepository;
import org.example.bankingManagementApplication.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role findByRoleName(String userRole) {
        return roleRepository.findByRoleName(userRole);
    }

    @Override
    public void saveRole(Role superAdminRole) {

    }

    @Override
    public void createRole(String userRole, RoleType roleType, Object o, List<Privilege> consumerPrivilege) {

    }

    @Override
    public List<Role> getUserRoles(User user) {
        return new ArrayList<>(user.getRoles());
    }
}
