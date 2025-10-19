package org.example.bankingManagementApplication.serviceImpl;

import org.example.bankingManagementApplication.entity.User;
import org.example.bankingManagementApplication.model.response.UserResponse;
import org.example.bankingManagementApplication.service.UserServiceRestClient;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceRestClientImpl implements UserServiceRestClient {

    private final RestClient restClient;
    private final ModelMapper modelMapper;

    public UserServiceRestClientImpl(RestClient restClient, ModelMapper modelMapper) {
        this.restClient = restClient;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserResponse> getAllUser() {

        User[] users = restClient.get()
                .uri("/users")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(User[].class);

        List<User> userList = Arrays.asList(users);

        return userList
                .stream()
                .map(user -> modelMapper.map(user,UserResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Long id) {

        User user = restClient.get()
                .uri("/users/{id}",id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(User.class);
        return modelMapper.map(user, UserResponse.class);
    }
}
