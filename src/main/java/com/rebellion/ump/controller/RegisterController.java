package com.rebellion.ump.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rebellion.ump.entity.User;
import com.rebellion.ump.repository.UserRepo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("register")
public class RegisterController {

    private UserRepo userRepo;

    public RegisterController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public User postRegisterForm(@RequestParam String name, @RequestParam String mobile, @RequestParam String email,
            @RequestParam String userid, @RequestParam String password, @RequestParam(defaultValue = "false") boolean tfa) {
        User user = new User();
        user.setName(name);
        user.setMobile(mobile);
        user.setEmail(email);
        user.setUserid(userid);
        user.setPassword(password);
        user.setTfa(tfa);
        return userRepo.save(user);
    }

}
