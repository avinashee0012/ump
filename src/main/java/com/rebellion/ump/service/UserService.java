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

    public User searchById(Long id){
        return userRepo.findOneById(id);
    }

    public void deleteUserById(Long id){
        userRepo.deleteById(id);
    }

    public User updateUserByEmail(User user_inp){
        User db_user = userRepo.findByEmail(user_inp.getEmail());
        db_user.setName(user_inp.getName());
        db_user.setMobile(user_inp.getMobile());
        db_user.setPassword(user_inp.getPassword());
        db_user.setTfa(user_inp.getTfa());
        db_user.setIsVerified(user_inp.getIsVerified());
        return userRepo.save(db_user);
    }

}
