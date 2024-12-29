package com.rebellion.ump.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rebellion.ump.entity.User;
import com.rebellion.ump.service.EmailService;
import com.rebellion.ump.service.UserService;

import javax.mail.MessagingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("login")
public class LoginController {

    private UserService userService;
    private EmailService emailService;

    public LoginController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @SuppressWarnings("null")
    @PostMapping(path = "/auth")
    public ResponseEntity<?> postLoginAuth(@RequestParam String email, @RequestParam String password, @RequestParam String otp)
            throws MessagingException {
        if (email != null && password != null) {
            User user = userService.searchByEmail(email);
            if (!user.getIsVerified()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else if (!user.getTfa() && user.getPassword().equals(password)) {
                return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
            } else if (user.getTfa() && user.getPassword().equals(password) && user.getToken().equals(otp) ) {
                return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
            } else if (user.getTfa() && user.getPassword().equals(password)) {
                String twoStepOTP = emailService.twoStepAuthentication(email).getBody().toString();
                user.setToken(twoStepOTP);
                userService.saveUser(user);
                return new ResponseEntity<>(HttpStatus.TEMPORARY_REDIRECT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

}
