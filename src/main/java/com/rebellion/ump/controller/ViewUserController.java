package com.rebellion.ump.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rebellion.ump.entity.User;
import com.rebellion.ump.service.UserService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class ViewUserController {

    private UserService userService;

    public ViewUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("view_user/{email}")
    public User getOneUser(@PathVariable String email) {
        return userService.searchByEmail(email);
    }

    @GetMapping("view_user/all")
    public List<User> getAllUsers() {
        return userService.searchAllUsers();
    }
    
}
