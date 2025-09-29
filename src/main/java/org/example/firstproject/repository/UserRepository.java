package org.example.firstproject.repository;

import org.example.firstproject.entity.User;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.email = ?1")
    Optional<User> findByEmail(String email);

    Optional<User> findTopByEmail(String email);

    @Query("select  u from User u where u.name = ?1")
    Optional<User> findByName (String name);
    List<User> findDistinctByName(String name);

    @Query("select distinct u.name from User u order by u.name desc limit 05")
    List<String> findUniqueByName();

    List<User> findByNameStartingWith(String keyword);

    List<User> findByNameEndingWith(String name);

    List<User> findByNameContains(String name);





}
