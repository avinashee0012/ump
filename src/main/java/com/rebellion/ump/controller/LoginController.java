package com.rebellion.ump.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rebellion.ump.entity.User;
import com.rebellion.ump.repository.UserRepo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("login")
public class LoginController {

    private UserRepo userRepo;

    public LoginController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping(path = "/auth")
    public ResponseEntity<HttpStatus> postLoginAuth(@RequestParam String userid, @RequestParam String password) {
        if(userid != null && password != null){
            User user = userRepo.findByUserid(userid);
            if(user.getPassword().equals(password)){
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
