package org.example.firstproject;

import org.example.firstproject.constatnt.AppConstants;
import org.example.firstproject.constatnt.AppTables;
import org.example.firstproject.entity.Role;
import org.example.firstproject.entity.User;
import org.example.firstproject.enums.RoleType;
import org.example.firstproject.repository.RoleRepository;
import org.example.firstproject.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

import static org.example.firstproject.constatnt.AppConstants.userRole;

@SpringBootApplication
public class FirstProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstProjectApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            Role userRole = roleRepository.findByRoleName("ROLE_USER");
            if (userRole == null) {
                userRole = new Role();
                userRole.setRoleName("ROLE_USER");
                userRole.setRoleType(RoleType.USER);
                roleRepository.save(userRole);
            }

            Set<Role> roles = Set.of(userRole);
            if (!userRepository.existsUserByEmail("intern@gmail.com")) {
                userRepository.save(new User(
                        "intern",
                        passwordEncoder.encode("password123"),
                        "intern@gmail.com",
                        roles
                ));
            }

            Role adminRole = roleRepository.findByRoleName("ROLE_ADMIN");
            if (adminRole == null) {
                adminRole = new Role();
                adminRole.setRoleName("ROLE_ADMIN");
                adminRole.setRoleType(RoleType.ADMIN);
                roleRepository.save(adminRole);
            }
            Set<Role> adminRoles = Set.of(adminRole);

            if (!userRepository.existsUserByEmail("admin@gmail.com")) {
                userRepository.save(new User(
                        "admin",
                        passwordEncoder.encode("admin123"),
                        "admin@gmail.com",
                        adminRoles
                ));
            }
        };
    }

}
