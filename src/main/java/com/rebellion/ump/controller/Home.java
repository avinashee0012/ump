package com.rebellion.ump.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("")
public class Home {

    @GetMapping("")
    public String getHome() {
        return "Welcome to User Management Portal (UMP)";
    }
    
}
