package com.ssst.homefinderbackend.config;

import com.ssst.homefinderbackend.data.entity.RoleEntity;
import com.ssst.homefinderbackend.data.repository.RoleRepo;
import com.ssst.homefinderbackend.data.repository.UserRepo;
import com.ssst.homefinderbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final RoleRepo roleRepository;
    private final UserService userService;

    private final UserRepo userRepository;

    @Autowired
    public DatabaseInitializer(RoleRepo roleRepository,
                               UserService userService,
                               UserRepo userRepository) {
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if roles already exist
        if (!roleRepository.existsByName("admin")) {
            RoleEntity adminRole = new RoleEntity();
            adminRole.setName("admin");
            roleRepository.save(adminRole);
        }


        if (!roleRepository.existsByName("user")) {
            RoleEntity userRole = new RoleEntity();
            userRole.setName("user");
            roleRepository.save(userRole);
        }

        if (!userRepository.existsByUsername("test@example.com")) {
            this.userService.createFirstAdminUser();
        }
    }
}
