package com.rebellion.ump.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.rebellion.ump.entity.User;
import com.rebellion.ump.service.EmailService;
import com.rebellion.ump.service.UserService;

import javax.mail.MessagingException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("register")
public class RegisterController {

    private UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView postRegisterForm(@RequestParam String name, @RequestParam String mobile, @RequestParam String email, @RequestParam String password, @RequestParam(defaultValue = "false") boolean tfa) throws MessagingException {
        User user = new User();
        String errorRedirect = "redirect:/register.html?status=error";
        if (name.equals("") || name == null) {
            return new ModelAndView(errorRedirect);
        }
        if (mobile.equals("") || mobile == null) {
            return new ModelAndView(errorRedirect);
        }
        if (email.equals("") || email == null) {
            return new ModelAndView(errorRedirect);
        }
        if (password.equals("") || password == null) {
            return new ModelAndView(errorRedirect);
        }

        // This validation needs to be taken to client-side
        if(isUnique(email)) {
            user.setEmail(email);
        } else {
            return new ModelAndView(errorRedirect);
        }
        user.setName(name);
        user.setMobile(mobile);
        user.setPassword(password);
        // DP Algo to be created
        EmailService.sendVerificationEmail(email);
        userService.saveUser(user);
        
        return new ModelAndView("redirect:/register.html?status=success");
    }
    
    // This validation needs to be taken to client-side
    private boolean isUnique(String email) {
        if(userService.searchByEmail(email) != null){
            return false;
        } 
        return true;
        
    }    

}
