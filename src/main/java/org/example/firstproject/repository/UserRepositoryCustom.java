package org.example.firstproject.repository;

import org.example.firstproject.entity.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> findByName (String name);
}
