package com.ssst.homefinderbackend.service;

import com.ssst.homefinderbackend.data.entity.RoleEntity;
import com.ssst.homefinderbackend.data.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepository;

    public RoleEntity createNewRole(RoleEntity role) {
        return roleRepository.save(role);
    }
}