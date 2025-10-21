package org.example.bankingManagementApplication.controller;

import lombok.RequiredArgsConstructor;
import org.example.bankingManagementApplication.entity.Role;
import org.example.bankingManagementApplication.entity.User;
import org.example.bankingManagementApplication.model.response.HttpResponse;
import org.example.bankingManagementApplication.service.PrivilegeService;
import org.example.bankingManagementApplication.service.RoleService;
import org.example.bankingManagementApplication.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/role")
public class RoleController {

    private final RoleService roleService;
    private final PrivilegeService privilegeService;
    private final UserService userService;

    @GetMapping("/user-role")
    public ResponseEntity<HttpResponse> getUserRoles(@RequestParam("userEmail") String userEmail){
        User user = userService.findByEmail(userEmail);
        List<Role> userRoles = roleService.getUserRoles(user);
        return HttpResponse.getResponseEntity(HttpStatus.OK,"Data loaded successfully",userRoles,true);
    }

}
