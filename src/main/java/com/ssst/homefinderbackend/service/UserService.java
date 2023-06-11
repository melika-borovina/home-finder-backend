package com.ssst.homefinderbackend.service;


import com.ssst.homefinderbackend.data.entity.RoleEntity;
import com.ssst.homefinderbackend.data.entity.UserEntity;
import com.ssst.homefinderbackend.data.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    public UserEntity registerNewUser(UserEntity user) {
        RoleEntity role = new RoleEntity();
        role.setId(2); // Default role "user"

        List<RoleEntity> roles = new ArrayList<>();
        roles.add(role);

        user.setRoles(roles);
        return userRepository.save(user);
    }

}