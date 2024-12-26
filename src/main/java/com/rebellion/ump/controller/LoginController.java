package com.rebellion.ump.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
    public ResponseEntity<?> postLoginAuth(@RequestParam String email, @RequestParam String password)
            throws MessagingException {
        if (email != null && password != null) {
            User user = userService.searchByEmail(email);
            if (!user.getIsVerified()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else if (user.getPassword().equals(password) && !user.getTfa()) {
                return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
            } else if (user.getPassword().equals(password) && user.getTfa()) {
                String twoStepOTP = emailService.twoStepAuthentication(email).getBody().toString();
                user.setToken(twoStepOTP);
                userService.saveUser(user);
                return new ResponseEntity<>(HttpStatus.TEMPORARY_REDIRECT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping(path = "/twoStepAuth")
    public ResponseEntity<?> twoStepAuth(@RequestParam String email, @RequestParam String password, @RequestParam String otp) {
        User user = userService.searchByEmail(email);
        System.out.println(user.getPassword().equals(password));
        System.out.println(user.getToken().equals(otp));
        if (user.getPassword().equals(password) && user.getToken().equals(otp)) {
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

}
