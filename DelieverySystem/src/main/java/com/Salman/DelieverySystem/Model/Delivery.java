package com.Salman.DelieverySystem.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "delivery")
public class Delivery {

    public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductDescription() {
		return productDescription;
	}


	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}


	public String getToContactNumber() {
		return toContactNumber;
	}


	public void setToContactNumber(String toContactNumber) {
		this.toContactNumber = toContactNumber;
	}


	public String getFromContactNumber() {
		return fromContactNumber;
	}


	public void setFromContactNumber(String fromContactNumber) {
		this.fromContactNumber = fromContactNumber;
	}


	public String getQuantity() {
		return quantity;
	}


	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	public String getToAddress() {
		return toAddress;
	}


	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}


	public String getFromAddress() {
		return fromAddress;
	}


	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getRiderId() {
		return riderId;
	}


	public void setRiderId(int riderId) {
		this.riderId = riderId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Rider getRider() {
		return rider;
	}


	public void setRider(Rider rider) {
		this.rider = rider;
	}


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
