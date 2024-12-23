package com.rebellion.ump.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rebellion.ump.entity.User;
import com.rebellion.ump.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("login")
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/auth")
    public ResponseEntity<HttpStatus> postLoginAuth(@RequestParam String email, @RequestParam String password) {
        if(email != null && password != null){
            User user = userService.searchByEmail(email);
            if(user.getPassword().equals(password)){
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
