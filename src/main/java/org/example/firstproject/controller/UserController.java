package org.example.firstproject.controller;

import jakarta.validation.Valid;
import org.example.firstproject.dto.UserDto;
import org.example.firstproject.entity.User;
import org.example.firstproject.model.PaginationArgs;
import org.example.firstproject.model.response.HttpResponse;
import org.example.firstproject.model.response.UserResponse;
import org.example.firstproject.service.UserService;
import org.example.firstproject.utils.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.firstproject.constatnt.AppConstants.*;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final PaginationUtil paginationUtil;

    public UserController(UserService userService, PaginationUtil paginationUtil) {
        this.userService = userService;
        this.paginationUtil = paginationUtil;
    }

    @GetMapping("/get-all-user")
    public ResponseEntity<HttpResponse> getAllUsers() {
        List<UserResponse> allUsers = userService.getAllUsers();
        return HttpResponse.getResponseEntity(HttpStatus.OK, allUsers);
    }

    @GetMapping("/all-user")
    public ResponseEntity<HttpResponse> getAllUserPaginated(
            @RequestParam(name = PAGE_NO, defaultValue = "0") int pageNo,
            @RequestParam(name = PAGE_SIZE, defaultValue = "10") int pageSize,
            @RequestParam(name = SORT_BY, defaultValue = "id") String sortBy,
            @RequestParam(name = SORT_ORDER, defaultValue = "asc") String sortOrder) {

        PaginationArgs paginationArgs = new PaginationArgs(pageNo, pageSize, sortBy, sortOrder);
        Page<User> page = userService.getAllUserPaginated(paginationArgs);
        return HttpResponse.getResponseEntity(HttpStatus.OK, paginationUtil.buildingPaginatedResponse(page));
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<HttpResponse> getUser(@PathVariable Long id) {
        UserResponse userResponse = userService.getUser(id);
        return HttpResponse.getResponseEntity(HttpStatus.OK, "User fetched successfully", userResponse, true);
    }

    @GetMapping("/get-user/byEmail")
    public ResponseEntity<HttpResponse> getUserByEmailAddress(@RequestParam String email) {

        UserResponse userResponse = userService.getUserByEmail(email);
        return HttpResponse.getResponseEntity(HttpStatus.OK, "User fetched successfully", userResponse, true);
    }


    @PutMapping("/update-user/{id}")
    public ResponseEntity<HttpResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(id, userDto);
        return HttpResponse.getResponseEntity(HttpStatus.OK, "User info updated successfully", updatedUser, true);
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<HttpResponse> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return HttpResponse.getResponseEntity(true, "Deleted successfully");
    }


}
