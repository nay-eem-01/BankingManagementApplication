package org.example.bankingManagementApplication.controller;

import lombok.RequiredArgsConstructor;
import org.example.bankingManagementApplication.entity.Privilege;
import org.example.bankingManagementApplication.model.response.HttpResponse;
import org.example.bankingManagementApplication.service.PrivilegeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/privilege")
@RequiredArgsConstructor
public class PrivilegeController {

    private final PrivilegeService privilegeService;

    @GetMapping("/get-privileges")
    public ResponseEntity<HttpResponse> getALlPrivilege() {
        List<Privilege> privileges = privilegeService.getAllPrivilege();
        return HttpResponse.getResponseEntity(HttpStatus.OK, privileges);
    }
}
