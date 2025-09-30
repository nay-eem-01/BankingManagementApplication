package org.example.firstproject.controller;

import jakarta.validation.Valid;
import org.example.firstproject.dto.UserDto;
import org.example.firstproject.entity.User;
import org.example.firstproject.model.response.HttpResponse;
import org.example.firstproject.model.response.PaginatedResponse;
import org.example.firstproject.model.PaginationArgs;
import org.example.firstproject.model.request.SignUpRequest;
import org.example.firstproject.model.response.UserResponse;
import org.example.firstproject.service.UserService;
import org.example.firstproject.service.UserServiceRestClient;
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
    private final UserServiceRestClient userServiceRestClient;

    public UserController(UserService userService, PaginationUtil paginationUtil, UserServiceRestClient userServiceRestClient) {
        this.userService = userService;
        this.paginationUtil = paginationUtil;
        this.userServiceRestClient = userServiceRestClient;
    }

    @PostMapping("/create-user")
    public ResponseEntity<HttpResponse> createUser(@Valid @RequestBody SignUpRequest signUpRequest){
        userService.createNewUser(signUpRequest);
        return HttpResponse.getResponseEntity(true,"User created successfully");
    }

    // Get users

    @GetMapping("/get-all-user")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<HttpResponse> getUser (@PathVariable Long id){
        UserResponse userResponse = userService.getUser(id);
        return  HttpResponse.getResponseEntity(HttpStatus.OK,"User fetched successfully",userResponse);
    }

    @GetMapping("/get-user/byEmail")
    public ResponseEntity<HttpResponse> getUserByEmailAddress(@RequestParam String email){

        UserResponse userResponse = userService.getUserByEmail(email);
        return HttpResponse.getResponseEntity(HttpStatus.OK,"User fetched successfully",userResponse);
    }


    @GetMapping("/get-user/username")
    public ResponseEntity<HttpResponse> getUserByUsername(@RequestParam String username){
        List<UserResponse> userResponse = userService.getUserByUsername(username);
        return HttpResponse.getResponseEntity(HttpStatus.OK,"User fetched successfully",userResponse);
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<HttpResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto){
        UserDto updatedUser = userService.updateUser(id,userDto);
        return HttpResponse.getResponseEntity(HttpStatus.OK,"User info updated successfully",updatedUser);
    }



    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<HttpResponse> deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
        return HttpResponse.getResponseEntity(true,"Deleted successfully");
    }

    @GetMapping("/name-list")
    public ResponseEntity<List<String>> getAllUniqueName(){
        List<String> names = userService.getAllUniqueName();
        return new ResponseEntity<>(names,HttpStatus.OK);
    }

    @GetMapping("/name-list/startsWith")
    public ResponseEntity<List<User>> getUserNameStartsWith(@RequestParam String keyword){
        List<User> users = userService.getUserNameStartsWith(keyword);

        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/name-list/endsWith")
    public ResponseEntity<List<User>> getUserNameEndsWith(@RequestParam String keyword){
        List<User> users = userService.getUserNameEndsWith(keyword);

        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/name-list/contains")
    public ResponseEntity<List<User>> getUserNameContains(@RequestParam String keyword){
        List<User> users = userService.getUserNameContains(keyword);

        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/user/criteria")
    public ResponseEntity<List<User>> getUserByCriteria(@RequestParam String keyword){
        List<User> users = userService.getUserByCriteria(keyword);

        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/all-user")
    public ResponseEntity<PaginatedResponse<User>> getAllUserPaginated(
            @RequestParam(name = PAGE_NO,defaultValue = "0") int pageNo,
            @RequestParam(name = PAGE_SIZE,defaultValue = "10") int pageSize,
            @RequestParam(name = SORT_BY,defaultValue = "id") String sortBy,
            @RequestParam(name = SORT_ORDER,defaultValue = "asc") String sortOrder){

        PaginationArgs paginationArgs = new PaginationArgs(pageNo,pageSize,sortBy,sortOrder);
        Page<User> page = userService.getAllUserPaginated(paginationArgs);
        return new ResponseEntity<>(paginationUtil.buildingPaginatedResponse(page),HttpStatus.OK);
    }

    @GetMapping("/user/ext")
    public ResponseEntity<HttpResponse> getAllUserFromExternalAPI(){
        List<UserResponse> users = userServiceRestClient.getAllUser();
        return HttpResponse.getResponseEntity(HttpStatus.OK,"User fetched successfully",users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<HttpResponse> getUserFromExternalAPI(@PathVariable Long id){
        UserResponse user = userServiceRestClient.getUserById(id);
        return HttpResponse.getResponseEntity(HttpStatus.OK,"User fetched successfully",user);
    }

    @GetMapping("/user-spec")
    public ResponseEntity<HttpResponse> getUserNameStartsWithSpecification(@RequestParam String name,@RequestParam String email){
        List<User> users = userService.users(name,email);
        System.out.println(users);
        return HttpResponse.getResponseEntity(HttpStatus.OK,"Success",users);

    }

    @GetMapping("/old-user")
    public ResponseEntity<HttpResponse> getLongTimeUser(@RequestParam Long days){
        List<User> userList = userService.olderUser(days);
        return HttpResponse.getResponseEntity(HttpStatus.OK,"User fetched successfully",userList);
    }

    @GetMapping("/valid-user")
    public ResponseEntity<HttpResponse> validUser(@RequestParam Long days){
        List<User> userList = userService.validUser(days);
        return HttpResponse.getResponseEntity(HttpStatus.OK,"User fetched successfully",userList);
    }




}
