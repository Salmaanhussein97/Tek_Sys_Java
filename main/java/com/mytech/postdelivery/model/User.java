package com.mytech.postdelivery.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="myuser")
@Data

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

//    @NotBlank(message = "Name field is required !!")
//    @Size(min = 2,max = 20,message = "min 2 and max 20 characters are allowed !!")
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String role;
    private String phone;
    private String address;
    private boolean enabled;
    @Column(length = 500)
    private String about;




}
