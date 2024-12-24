package com.rebellion.ump.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.rebellion.ump.entity.User;
import com.rebellion.ump.service.EmailService;
import com.rebellion.ump.service.UserService;

import java.util.HashMap;

import javax.mail.MessagingException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("register")
public class RegisterController {

    private UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @SuppressWarnings("null")
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
        user.setVerificationToken(EmailService.sendVerificationEmail(email).getBody().toString());
        userService.saveUser(user);
        
        return new ModelAndView("redirect:/register.html?status=success");
    }

    @GetMapping("verify/{email}")
    public ModelAndView verifyEmail(@PathVariable String email, @RequestParam String token) {
        if(userService.searchByEmail(email) != null){
            User user = userService.searchByEmail(email);
            if (!user.getIsVerified() && token.equals(user.getVerificationToken())) {
                user.setIsVerified(true);
                userService.saveUser(user);
                return new ModelAndView("redirect:/verification.html?status=success");
            } else if (user.getIsVerified()) {
                return new ModelAndView("redirect:/verification.html?status=alreadyVerified");
            }
        }
        return new ModelAndView("redirect:/verification.html?status=error");
    }
    
    
    // This validation needs to be taken to client-side
    private boolean isUnique(String email) {
        if(userService.searchByEmail(email) != null){
            return false;
        } 
        return true;
        
    }

}
