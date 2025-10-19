package org.example.bankingManagementApplication.repository;

import org.example.bankingManagementApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByEmail(String email);
    Boolean existsUserByFullName(String name);
    Boolean existsUserByEmail(String email);
    Optional<User> findTopByEmail(String email);
    List<User> findAllByFullName(String name);


}
