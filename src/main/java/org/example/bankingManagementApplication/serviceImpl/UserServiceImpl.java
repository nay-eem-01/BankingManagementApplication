package org.example.bankingManagementApplication.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bankingManagementApplication.constatnt.AppConstants;
import org.example.bankingManagementApplication.dto.UserDto;
import org.example.bankingManagementApplication.entity.Role;
import org.example.bankingManagementApplication.entity.User;
import org.example.bankingManagementApplication.exceptionHandler.AuthenticationExceptionImpl;
import org.example.bankingManagementApplication.exceptionHandler.ResourceNotFoundException;
import org.example.bankingManagementApplication.exceptionHandler.UserAlreadyExistsException;
import org.example.bankingManagementApplication.model.PaginationArgs;
import org.example.bankingManagementApplication.model.request.SignInRequest;
import org.example.bankingManagementApplication.model.request.SignUpRequest;
import org.example.bankingManagementApplication.model.response.LoginResponse;
import org.example.bankingManagementApplication.model.response.UserResponse;
import org.example.bankingManagementApplication.repository.UserRepository;
import org.example.bankingManagementApplication.security.jwt.JwtUtil;
import org.example.bankingManagementApplication.service.RoleService;
import org.example.bankingManagementApplication.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final JwtUtil jwtUtil;

    @Override
    public void signUp(SignUpRequest signUpRequest) {

        User userExist = userRepository.findTopByEmail(signUpRequest.getEmail()).orElse(null);
        if (userExist != null) {
            log.error("User already exists with email: {}", signUpRequest.getEmail());
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
        log.info("Assigned role:{}", role.toString());

        signedInUser.setRoles(Set.of(role));

        userRepository.save(signedInUser);

        log.info("User created successfully: {}", signedInUser);
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
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
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

        User user = userRepository.findTopByEmail(loginRequest.getEmail()).orElseThrow(() -> new ResourceNotFoundException("No user found with this email"));

        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            log.info("Authentication successful for: {}, Authorities: {}", authentication.getName(), authentication.getAuthorities());

        } catch (BadCredentialsException exception) {

            log.warn("Authentication failed: Invalid credentials for email - {}", loginRequest.getEmail());
            log.debug("Authentication Exception:", exception);

            throw new BadCredentialsException("Invalid email or password");

        } catch (AuthenticationException exception) {

            log.error("Unexpected authentication exception for email - {}: {}", loginRequest.getEmail(), exception.getMessage());
            log.debug("Authentication Exception: ", exception);

            throw new AuthenticationExceptionImpl("Unknown authentication exception occurred");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.generateAccessToken(authentication);
        return new LoginResponse(jwt, AppConstants.JWT_TOKEN_TYPE, userResponse);
    }


}
