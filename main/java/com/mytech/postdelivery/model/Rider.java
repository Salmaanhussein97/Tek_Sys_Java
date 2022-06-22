package com.mytech.postdelivery.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="rider")
@Data
@Getter
@Setter

public class Rider {
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
