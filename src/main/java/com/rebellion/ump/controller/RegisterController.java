package com.rebellion.ump.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.rebellion.ump.entity.User;
import com.rebellion.ump.repository.UserRepo;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("register")
public class RegisterController {

    private UserRepo userRepo;

    public RegisterController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping(path = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView postRegisterForm(@RequestParam String name, @RequestParam String mobile, @RequestParam String email,
            @RequestParam String userid, @RequestParam String password, @RequestParam(defaultValue = "false") boolean tfa, @RequestParam MultipartFile dp) throws IOException {
        User user = new User();
        StringBuilder sb = new StringBuilder();
        DataInputStream input = new DataInputStream( dp.getInputStream() );
       try {
            while( true ) {
                sb.append( Integer.toBinaryString( input.readByte() ) );
            }
       } catch ( Exception e) {
            System.out.println(e);
       } finally {
            input.close();
       }
        user.setName(name);
        user.setMobile(mobile);
        user.setEmail(email);
        user.setUserid(userid);
        user.setPassword(password);
        user.setTfa(tfa);
        user.setDp(sb.toString());
        userRepo.save(user);
        return new ModelAndView("redirect:/register?success");
    }

}
