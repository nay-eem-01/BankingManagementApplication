package org.example.bankingManagementApplication.controller;

import lombok.RequiredArgsConstructor;
import org.example.bankingManagementApplication.service.PrivilegeService;
import org.example.bankingManagementApplication.service.RoleService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    private final PrivilegeService privilegeService;

}
