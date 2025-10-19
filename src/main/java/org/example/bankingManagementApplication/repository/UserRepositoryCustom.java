package org.example.bankingManagementApplication.repository;

import org.example.bankingManagementApplication.entity.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> findByName (String name);
}
