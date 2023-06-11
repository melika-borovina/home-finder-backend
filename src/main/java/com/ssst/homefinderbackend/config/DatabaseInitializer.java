package com.ssst.homefinderbackend.config;

import com.ssst.homefinderbackend.data.entity.RoleEntity;
import com.ssst.homefinderbackend.data.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final RoleRepo roleRepository;

    @Autowired
    public DatabaseInitializer(RoleRepo roleRepository) {
        this.roleRepository = roleRepository;
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
    }
}
