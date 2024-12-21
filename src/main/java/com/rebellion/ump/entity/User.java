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
    @Column
    private String dp;
    @Column
    private Boolean tfa;
}
