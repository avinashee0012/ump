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
    @Column
    private String email;
    @Column
    private String userid;
    @Column
    private String password;
    // Not familiar with suitable DATATYPE for an image
    // @Column
    // private DATATYPE dp;
    @Column(name="2fa")
    private boolean tfa;
}
