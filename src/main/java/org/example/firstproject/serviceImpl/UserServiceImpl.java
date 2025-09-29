package org.example.firstproject.serviceImpl;

import org.example.firstproject.dto.UserDto;
import org.example.firstproject.entity.User;
import org.example.firstproject.model.PaginationArgs;
import org.example.firstproject.model.request.SignUpRequest;
import org.example.firstproject.model.response.UserResponse;
import org.example.firstproject.repository.UserRepository;
import org.example.firstproject.repository.UserRepositoryCustom;
import org.example.firstproject.security.PasswordEncoder;
import org.example.firstproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final UserRepositoryCustom userRepositoryCustom;



    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, UserRepositoryCustom userRepositoryCustom) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.userRepositoryCustom = userRepositoryCustom;
    }


    @Override
    public void createNewUser(SignUpRequest signUpRequest) {
        User userExist = userRepository.findTopByEmail(signUpRequest.getEmail()).orElse(null);

        String username = signUpRequest.getName();
        String email = signUpRequest.getEmail();
        String password = signUpRequest.getPassword();

        User signedInUser = new User();

        System.out.println(username);
        System.out.println(email);
        System.out.println(password);

        signedInUser.setEmail(email);
        signedInUser.setName(username);
        signedInUser.setPassword(password);

        userRepository.save(signedInUser);

    }

    @Override
    public List<UserResponse> getAllUsers() {

        List<User> allUsers = userRepository.findAll();
        List<UserResponse> allUserResponse = allUsers
                .stream()
                .map(user ->
                        modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
        return allUserResponse;
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
        List<User>  users = userRepository.findDistinctByName(username);
        List<UserResponse> userResponses = users.stream()
                .map(user ->
                        modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
        return userResponses;
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {

        User updatedUser = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User does not exist"));

        if (userDto.getName() != null) {
            updatedUser.setName(userDto.getName());
        }

        if (userDto.getEmail() != null) {
            updatedUser.setEmail(userDto.getEmail());
        }

        return modelMapper.map(userRepository.save(updatedUser), UserDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User does not exist"));
        userRepository.delete(user);

    }

    @Override
    public List<String> getAllUniqueName() {
        List<String> names = userRepository.findUniqueByName();
        return names;
    }

    @Override
    public List<User> getUserNameStartsWith(String keyword) {
        return userRepository.findByNameStartingWith(keyword);
    }

    @Override
    public List<User> getUserNameEndsWith(String keyword) {
        return userRepository.findByNameEndingWith(keyword);
    }

    @Override
    public List<User> getUserNameContains(String keyword) {
        return userRepository.findByNameContains(keyword);
    }

    @Override
    public List<User> getUserByCriteria(String name) {
        return userRepositoryCustom.findByName(name);
    }

    @Override
    public Page<User> getAllUserPaginated(PaginationArgs paginationArgs) {

        Sort sortByOrder = paginationArgs.getSortOrder().equalsIgnoreCase("asc")
                ? Sort.by(paginationArgs.getSortBy()).ascending()
                : Sort.by(paginationArgs.getSortBy()).descending();

        Pageable pageable = PageRequest.of(paginationArgs.getPageNo(), paginationArgs.getPageSize(),sortByOrder);

        return userRepository.findAll(pageable);
    }


}
