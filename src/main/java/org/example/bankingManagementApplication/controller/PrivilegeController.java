package org.example.bankingManagementApplication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.example.bankingManagementApplication.config.CommonApiResponses;
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
@CommonApiResponses
public class PrivilegeController {

    private final PrivilegeService privilegeService;

    @Operation(description = "Get all privilege", security = @SecurityRequirement(name = "jwtToken"))
    @ApiResponse(content = @Content(schema = @Schema(implementation = HttpResponse.class)), responseCode = "200")
    @GetMapping("/get-privileges")
    public ResponseEntity<HttpResponse> getALlPrivilege() {
        List<Privilege> privileges = privilegeService.getAllPrivilege();
        return HttpResponse.getResponseEntity(HttpStatus.OK, privileges);
    }
}
