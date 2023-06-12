package com.ssst.homefinderbackend.controller;

import com.ssst.homefinderbackend.data.entity.UserEntity;
import com.ssst.homefinderbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user")
@RestController
public class UserController {

        @Autowired
        private UserService userService;

        @PostMapping({"/register"})
        public UserEntity registerNewUser(@RequestBody UserEntity user) {
            return userService.registerNewUser(user);
        }

    @GetMapping("/{username}")
    public UserDetails getUserByUsername(@PathVariable("username") String username) {
        return userService.loadUserByUsername(username);
    }
    }


