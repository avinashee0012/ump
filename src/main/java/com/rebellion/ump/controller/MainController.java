package com.rebellion.ump.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("")
public class MainController {

    @GetMapping("")
    public ModelAndView getHome() {
        return new ModelAndView("redirect:/index.html");
    }
    
}
