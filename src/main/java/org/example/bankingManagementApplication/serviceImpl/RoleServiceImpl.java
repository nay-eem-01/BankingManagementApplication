package org.example.bankingManagementApplication.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.example.bankingManagementApplication.entity.Privilege;
import org.example.bankingManagementApplication.entity.Role;
import org.example.bankingManagementApplication.entity.User;
import org.example.bankingManagementApplication.enums.RoleType;
import org.example.bankingManagementApplication.exceptionHandler.AlreadyExistsExceptionHandler;
import org.example.bankingManagementApplication.exceptionHandler.ResourceNotFoundException;
import org.example.bankingManagementApplication.model.request.RoleCreateRequest;
import org.example.bankingManagementApplication.model.request.RoleUpdateRequest;
import org.example.bankingManagementApplication.repository.RoleRepository;
import org.example.bankingManagementApplication.service.PrivilegeService;
import org.example.bankingManagementApplication.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final PrivilegeService privilegeService;

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with this role name"));
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void createRole(RoleCreateRequest roleCreateRequest) {

        if (roleRepository.existsRoleByRoleName(roleCreateRequest.getRoleName())) {
            throw new AlreadyExistsExceptionHandler("Role with this role name already exists");
        }

        List<Privilege> privileges = Arrays
                .stream(roleCreateRequest.getPrivilegeId())
                .mapToObj(privilegeId -> {
                    Privilege privilege = privilegeService.findByPrivilegeId(privilegeId);
                    return privilege;
                })
                .toList();

        Role role = new Role();
        role.setRoleName(roleCreateRequest.getRoleName());
        role.setRoleType(roleCreateRequest.getRoleType());
        role.setPrivileges(privileges);

        roleRepository.save(role);
    }


    @Override
    public List<Role> getUserRoles(User user) {
        return new ArrayList<>(user.getRoles());
    }

    @Override
    public List<String> getRoleType() {
        List<String> roleTypes = Arrays.stream(RoleType.values()).map(Enum::name).toList();
        return roleTypes;
    }

    @Override
    public Role updateRole(RoleUpdateRequest roleUpdateRequest) {

        Role role = roleRepository.findByRoleName(roleUpdateRequest.getRoleName())
                .orElseThrow(() -> new ResourceNotFoundException("Role does not exist"));

        List<Privilege> privileges = new ArrayList<>();

        for (Long privilegeId : roleUpdateRequest.getPrivilegeId()) {
            Privilege privilege = privilegeService.findByPrivilegeId(privilegeId);
            privileges.add(privilege);
        }

        role.setRoleName(roleUpdateRequest.getRoleName());
        role.setRoleType(roleUpdateRequest.getRoleType());
        role.setPrivileges(privileges);
        return null;
    }

    @Override
    public void deleteRole(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role does not exist"));

        roleRepository.delete(role);
    }
}
