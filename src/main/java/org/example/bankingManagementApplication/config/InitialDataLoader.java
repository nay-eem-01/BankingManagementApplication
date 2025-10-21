package org.example.bankingManagementApplication.config;

import lombok.RequiredArgsConstructor;
import org.example.bankingManagementApplication.constatnt.AppConstants;
import org.example.bankingManagementApplication.entity.Privilege;
import org.example.bankingManagementApplication.entity.Role;
import org.example.bankingManagementApplication.entity.User;
import org.example.bankingManagementApplication.enums.RoleType;
import org.example.bankingManagementApplication.service.PrivilegeService;
import org.example.bankingManagementApplication.service.RoleService;
import org.example.bankingManagementApplication.service.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class InitialDataLoader implements ApplicationListener<ApplicationContextEvent> {

    private boolean alreadySetup = false;

    private final RoleService roleService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final PrivilegeService privilegeService;

    @Override
    public void onApplicationEvent(ApplicationContextEvent event) {
        List<Privilege> superAdminPrivileges = new ArrayList<>();

        for (Map.Entry<String,String> entry : AppConstants.PERMISSIONS.entrySet()){
            boolean ifPrivilegeExists = this.checkIfPrivilegeExist(entry.getKey());

            if (!ifPrivilegeExists){
                Privilege newPrivilege = privilegeService.createPrivilege(entry.getKey(),entry.getValue());
                superAdminPrivileges.add(newPrivilege);
            }
        }
        if (checkIfRoleExist(AppConstants.INITIAL_ROLE)){
            Role superAdminRole = roleService.findByRoleName(AppConstants.INITIAL_ROLE);
            superAdminRole.getPrivileges().addAll(superAdminPrivileges);
            roleService.saveRole(superAdminRole);
        }

        if (alreadySetup || checkIfSuperAdminExist()){
            return;
        }

        List<Privilege> consumerPrivilege = new ArrayList<>();
        Privilege newPrivilege = privilegeService.createPrivilege(AppConstants.consumerPermission,AppConstants.consumerPermissionDesc);
        superAdminPrivileges.add(newPrivilege);
        consumerPrivilege.add(newPrivilege);

        roleService.createRole(AppConstants.userRole, RoleType.USER,null,consumerPrivilege);
        roleService.createRole(AppConstants.INITIAL_ROLE, RoleType.ADMIN,null,superAdminPrivileges);

        Set<Role> roleSet = new HashSet<>();
        Role role = roleService.findByRoleName(AppConstants.INITIAL_ROLE);

        if (role != null){
            roleSet.add(role);
        }
        User superAdminUser = new User();
        superAdminUser.setRoles(roleSet);
        superAdminUser.setFullName(AppConstants.INITIAL_ROLE);
        superAdminUser.setEmail(AppConstants.INITIAL_USERNAME);
        superAdminUser.setPassword(passwordEncoder.encode(AppConstants.INITIAL_PASSWORD));
        userService.saveUser(superAdminUser);

        alreadySetup = true;
    }

    private boolean checkIfSuperAdminExist() {
        return userService.findByEmail(AppConstants.INITIAL_USERNAME) != null;
    }

    private boolean checkIfRoleExist(String roleName) {
        return roleService.findByRoleName(roleName) != null;
    }

    private boolean checkIfPrivilegeExist(String privilegeName) {
        return privilegeService.findByPrivilegeName(privilegeName) !=null;
    }
}
