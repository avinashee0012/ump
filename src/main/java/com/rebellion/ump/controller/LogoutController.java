package com.rebellion.ump.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rebellion.ump.service.UserService;

@RestController
@RequestMapping("logout")
public class LogoutController {

    private UserService userService;

    public LogoutController(UserService userService) {
        this.userService = userService;
    }

    // @GetMapping("")
    // public ModelAndView logoutMethod() {
    //     return new ModelAndView("redirect:/login.html");
    // }
}
