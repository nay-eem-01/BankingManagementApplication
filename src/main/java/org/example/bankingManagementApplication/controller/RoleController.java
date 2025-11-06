package org.example.bankingManagementApplication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.example.bankingManagementApplication.entity.Role;
import org.example.bankingManagementApplication.entity.User;
import org.example.bankingManagementApplication.model.request.RoleCreateRequest;
import org.example.bankingManagementApplication.model.request.RoleUpdateRequest;
import org.example.bankingManagementApplication.model.response.HttpResponse;
import org.example.bankingManagementApplication.repository.UserRepository;
import org.example.bankingManagementApplication.service.RoleService;
import org.example.bankingManagementApplication.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/role")
public class RoleController {

    private final RoleService roleService;
    private final UserService userService;

    @Operation(summary = "Get user roles",security = @SecurityRequirement(name = "jwtToken"))
    @ApiResponse(content = @Content(schema = @Schema(implementation = HttpResponse.class)), responseCode = "200")
    @GetMapping("/user-role")
    public ResponseEntity<HttpResponse> getUserRoles(@RequestParam("userEmail") String userEmail){

        User user = userService.findByEmail(userEmail);

        List<Role> userRoles = roleService.getUserRoles(user);

        return HttpResponse.getResponseEntity(HttpStatus.OK,"Data loaded successfully",userRoles,true);
    }

    @Operation(summary = "Get role types",security = @SecurityRequirement(name = "jwtToken"))
    @ApiResponse(content = @Content(schema = @Schema(implementation = HttpResponse.class)), responseCode = "200")
    @GetMapping("/types")
    public ResponseEntity<HttpResponse> getRoleTypes(){

        List<String> roleTypes = roleService.getRoleType();

        return HttpResponse.getResponseEntity(HttpStatus.OK,"Data loaded successfully",roleTypes,true);
    }

    @Operation(summary = "Create role",security = @SecurityRequirement(name = "jwtToken"))
    @ApiResponse(content = @Content(schema = @Schema(implementation = HttpResponse.class)), responseCode = "200")
    @PreAuthorize("hasAuthority('ROLE_CREATE')")
    @PostMapping("/create")
    public ResponseEntity<HttpResponse> createRole(@RequestBody RoleCreateRequest roleCreateRequest){

        roleService.createRole(roleCreateRequest);

        return HttpResponse.getResponseEntity(HttpStatus.CREATED,"Role created successfully",null,true);
    }

    @Operation(summary = "Update role",security = @SecurityRequirement(name = "jwtToken"))
    @ApiResponse(content = @Content(schema = @Schema(implementation = HttpResponse.class)), responseCode = "200")
    @PreAuthorize("hasAnyAuthority('ROLE_UPDATE')")
    @PutMapping("/update")
    public ResponseEntity<HttpResponse> updateRole(@RequestBody RoleUpdateRequest roleUpdateRequest){

        Role updatedRole = roleService.updateRole(roleUpdateRequest);

        return HttpResponse.getResponseEntity(HttpStatus.OK,"Role updated successfully",updatedRole,true);
    }

    @Operation(summary = "Delete role",security = @SecurityRequirement(name = "jwtToken"))
    @ApiResponse(content = @Content(schema = @Schema(implementation = HttpResponse.class)), responseCode = "200")
    @PreAuthorize("hasAnyAuthority('ROLE_DELETE')")
    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity<HttpResponse> deleteRole(@PathVariable Long roleId){

        roleService.deleteRole(roleId);

        return HttpResponse.getResponseEntity(HttpStatus.OK,"Role deleted",null,true);
    }

}
