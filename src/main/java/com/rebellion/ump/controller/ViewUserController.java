package com.rebellion.ump.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rebellion.ump.entity.User;
import com.rebellion.ump.service.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ViewUserController {

    private UserService userService;

    private String hidden= "_HIDDEN_";

    public ViewUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("view_user/{email}")
    public User getUser(@PathVariable String email) {
        User user = userService.searchByEmail(email);
        user.setToken(hidden);
        return user;
    }

//    This should update the data only if user exists already.
    @PostMapping("/edit_user")
    public User updateUser(@RequestBody User user_inp) {
        return userService.updateUserByEmail(user_inp);
    }

    @DeleteMapping("view_user")
    public User deleteUser(@RequestParam Long id) {
        User user = userService.searchById(id);
        userService.deleteUserById(id);
        return user;
    }

    @GetMapping("view_user/all")
    public List<User> getAllUsers() {
        List<User> users = userService.searchAllUsers();
        for(User user: users){
            user.setToken(hidden);
            user.setPassword(hidden);
        }
        return users;
    }
    
}
