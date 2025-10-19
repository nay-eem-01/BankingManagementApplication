package org.example.bankingManagementApplication.controller;

import org.example.bankingManagementApplication.service.PrivilegeService;
import org.example.bankingManagementApplication.service.RoleService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    private final RoleService roleService;
    private final PrivilegeService privilegeService;


    public RoleController(RoleService roleService, PrivilegeService privilegeService) {
        this.roleService = roleService;
        this.privilegeService = privilegeService;
    }

    
}
