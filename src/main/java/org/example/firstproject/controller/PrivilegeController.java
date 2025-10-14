package org.example.firstproject.controller;

import org.example.firstproject.entity.Privilege;
import org.example.firstproject.model.response.HttpResponse;
import org.example.firstproject.service.PrivilegeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/privilege")
public class PrivilegeController {

    private final PrivilegeService privilegeService;


    public PrivilegeController(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }

    @GetMapping("/get-privileges")
    public ResponseEntity<HttpResponse> getALlPrivilege() {
        List<Privilege> privileges = privilegeService.getAllPrivilege();
        return HttpResponse.getResponseEntity(HttpStatus.OK, privileges);
    }
}
