package com.ssst.homefinderbackend.controller;

import com.ssst.homefinderbackend.data.entity.UserEntity;
import com.ssst.homefinderbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/user")
@RestController
public class UserController {

        @Autowired
        private UserService userService;

        @PostMapping({"/register"})
        public UserEntity registerNewUser(@RequestBody UserEntity user) {
            return userService.registerNewUser(user);
        }
    }


