package com.example.demo.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;




@Entity
@Table(name = "Autehntication_token")
public class AuthenticationToken {
     
	
	 @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="id")
	 private Integer id;

	 private String token;////////token "3jvd2d342r24243223cfcvvabbbc"////////
	 
	 @Column(name = "created_date")
	 private Date createdDate;////////////
	 
	 //one to one relationship with user table
	 @OneToOne(targetEntity= User.class,  fetch = FetchType.EAGER)
	 @JoinColumn(nullable=false, name = "user_id")   //@Table(name = "Autehntication_token") Autehntication_token table's   "user_id"    link to  User     :id   //  nullable is false because you will always have a user  linked to the token table
	 private User user ;  ////////////////user ///////////////////////////???????????????????????

	
	 
	 
	 
	 //constructor takes a User as parameter////////////////////////////
	public AuthenticationToken(User user) {
		this.user = user; //only pass user here 
		this.createdDate = new Date();
		this.token= UUID.randomUUID().toString();  //The UUID is generated using a cryptographically strong "pseudorandom number" Returns:A randomly generated UUID
	}
	
	
     //AuthenticationToken   Default Constructor
	public AuthenticationToken() {
		
		// TODO Auto-generated constructor stub
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	 
	 public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	 
	 
     
}
