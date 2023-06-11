package com.ssst.homefinderbackend.controller;
import com.ssst.homefinderbackend.data.entity.RoleEntity;
import com.ssst.homefinderbackend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;
    @PostMapping({"/createNewRole"})
    public RoleEntity createNewRole(@RequestBody RoleEntity role) {
        return roleService.createNewRole(role);
    }
}