package org.example.firstproject.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.example.firstproject.constatnt.AppConstants;
import org.example.firstproject.dto.UserDto;
import org.example.firstproject.entity.Role;
import org.example.firstproject.entity.User;
import org.example.firstproject.exceptionHandler.ResourceNotFoundException;
import org.example.firstproject.exceptionHandler.UserAlreadyExistsException;
import org.example.firstproject.model.PaginationArgs;
import org.example.firstproject.model.request.SignInRequest;
import org.example.firstproject.model.request.SignUpRequest;
import org.example.firstproject.model.response.LoginResponse;
import org.example.firstproject.model.response.UserResponse;
import org.example.firstproject.repository.UserRepository;
import org.example.firstproject.security.jwt.JwtUtil;
import org.example.firstproject.service.RoleService;
import org.example.firstproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final JwtUtil jwtUtil;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, RoleService roleService, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public void createNewUser(SignUpRequest signUpRequest) {

        User userExist = userRepository.findTopByEmail(signUpRequest.getEmail()).orElse(null);
        if (userExist!= null){
            throw new UserAlreadyExistsException("User already exist with this email");
        }
        String username = signUpRequest.getFullName();
        String email = signUpRequest.getEmail();
        String password = signUpRequest.getPassword();

        User signedInUser = new User();
        signedInUser.setEmail(email);
        signedInUser.setFullName(username);
        signedInUser.setPassword(passwordEncoder.encode(password));

        Role role = roleService.findByRoleName("ROLE_USER");

        log.info(role.toString());

        signedInUser.setRoles(Set.of(role));

        userRepository.save(signedInUser);

        log.info("User created successfully:");
    }

    @Override
    public List<UserResponse> getAllUsers() {

        List<User> allUsers = userRepository.findAll();
        return allUsers
                .stream()
                .map(user ->
                        modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> getUserByUsername(String username) {
        List<User> users = userRepository.findAllByFullName(username);
        return users.stream()
                .map(user ->
                        modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {

        User updatedUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User does not exist"));

        if (userDto.getName() != null) {
            updatedUser.setFullName(userDto.getName());
        }
        if (userDto.getEmail() != null) {
            updatedUser.setEmail(userDto.getEmail());
        }
        return modelMapper.map(userRepository.save(updatedUser), UserDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User does not exist"));
        userRepository.delete(user);
    }

    @Override
    public Page<User> getAllUserPaginated(PaginationArgs paginationArgs) {

        Sort sortByOrder = paginationArgs.getSortOrder().equalsIgnoreCase("asc")
                ? Sort.by(paginationArgs.getSortBy()).ascending()
                : Sort.by(paginationArgs.getSortBy()).descending();

        Pageable pageable = PageRequest.of(paginationArgs.getPageNo(), paginationArgs.getPageSize(), sortByOrder);

        return userRepository.findAll(pageable);
    }

    @Override
    public LoginResponse login(SignInRequest loginRequest) {



        if (loginRequest == null) {
            log.debug("Log in request is empty");
            throw new ResourceNotFoundException("Empty credentials");

        }
        User user = userRepository.findTopByEmail(loginRequest.getEmail()).orElseThrow(()-> new ResourceNotFoundException("No user found with this email"));
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("Current User: {}, Authorities: {}",authentication.getName(),authentication.getAuthorities());

        String jwt = jwtUtil.generateAccessToken(authentication);


        return new LoginResponse(jwt,AppConstants.JWT_TOKEN_TYPE,userResponse);
    }


}
