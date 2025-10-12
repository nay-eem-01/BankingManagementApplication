package org.example.firstproject.controller;

import org.example.firstproject.service.PrivilegeService;
import org.example.firstproject.service.RoleService;
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
