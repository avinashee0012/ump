package com.rebellion.ump.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String mobile;
    @Column(unique=true)
    private String email;
    @Column
    private Boolean isVerified = false;
    @Column
    private String verificationToken = "";
    @Column
    private String password;
    // Fix Required: Image is not saving and displaying properly
    // @Column
    // private String dp;
    @Column
    private Boolean tfa = false;
}
