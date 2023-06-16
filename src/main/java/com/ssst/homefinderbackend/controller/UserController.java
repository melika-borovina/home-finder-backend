package com.ssst.homefinderbackend.controller;

import com.ssst.homefinderbackend.data.entity.UserEntity;
import com.ssst.homefinderbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public UserEntity getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserWithoutPassword(username);
    }

    @GetMapping("")
    ResponseEntity<List<UserEntity>> getUsers() {
        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/{username}/role")
    public UserEntity setUserRoleToOne(@PathVariable("username") String username) {
        return userService.setUserRoleToAdmin(username);
    }





}


