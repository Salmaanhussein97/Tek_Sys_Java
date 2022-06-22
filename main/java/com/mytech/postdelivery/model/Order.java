package com.mytech.postdelivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "myorder")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String productName;
    private String productDescription;
    private String toContactNumber;
    private String quantity;
    private String toAddress;

    @Transient
    private int userId;
    @Transient
    private int riderId;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "riderId")
    private Rider rider;
}
