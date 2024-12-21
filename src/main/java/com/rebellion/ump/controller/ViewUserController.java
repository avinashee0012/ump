package com.rebellion.ump.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rebellion.ump.entity.User;
import com.rebellion.ump.repository.UserRepo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class ViewUserController {

    private UserRepo userRepo;

    public ViewUserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("view_user/{userid}")
    public User getOneUser(@PathVariable String userid) {
        return userRepo.findByUserid(userid);
    }

    @GetMapping("view_user/all")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    
}
