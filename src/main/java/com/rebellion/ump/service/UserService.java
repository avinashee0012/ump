package com.rebellion.ump.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.rebellion.ump.entity.User;
import com.rebellion.ump.repository.UserRepo;

@Service
public class UserService {

    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;

    }

    public User saveUser(User user){
        return userRepo.save(user);
    }

    public User searchByEmail(String email){
        return userRepo.findByEmail(email);
    }

    public List<User> searchAllUsers(){
        return userRepo.findAll();
    }

}
