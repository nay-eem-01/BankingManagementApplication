package org.example.firstproject.repository;

import org.example.firstproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findTopByEmail(String email);
}
