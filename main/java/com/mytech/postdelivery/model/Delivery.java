package com.mytech.postdelivery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "delivery")
@Data
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String productName;
    private String productDescription;
    private String toContactNumber;
    private String fromContactNumber;
    private String quantity;
    private String toAddress;
    private String fromAddress;

    @Transient
    private int userId;
    @Transient
    private int riderId;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "userId")
    private User user;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "riderId")
    private Rider rider;


}
