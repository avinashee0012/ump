package com.rebellion.ump.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("")
public class MainController {

    // Fix Required: shows .html in URL
    @GetMapping("")
    public ModelAndView getHomePage() {
        return new ModelAndView("redirect:/index.html");
    }

    // Fix Required: shows .html in URL
    @GetMapping("login")
    public ModelAndView getLoginPage() {
        return new ModelAndView("redirect:/login.html");
    }

    // Fix Required: shows .html in URL
    @GetMapping("register")
    public ModelAndView getRegisterPage() {
        return new ModelAndView("redirect:/register.html");
    }

    // Fix Required: shows .html in URL
    @GetMapping("users")
    public ModelAndView getUsersPage() {
        return new ModelAndView("redirect:/users.html");
    }
    
    // Fix Required: shows .html in URL
    @GetMapping("view_user")
    public ModelAndView getViewUserPage() {
        return new ModelAndView("redirect:/view_user.html");
    }
    
}
