package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name= "whishList")
public class WishList {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	
	@OneToOne(targetEntity =User.class, fetch= FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	
	@Column( name="createde_date")
	private Date createdDate;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;  //many wishlist object can have 1 kind if product    /////1 product can show up in mamy wishlist object ,then the product is the one>?

	
	
	////////////////this one is used!!!!!!!!!!!!!
	public WishList(User user, Product product) {
		super();
		this.user = user;
		this.product = product;
		this .createdDate= new Date();
	}


	public WishList(Integer id, User user, Date createdDate, Product product) {
		super();
		this.id = id;
		this.user = user;
		this.createdDate = createdDate;
		this.product = product;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
	
}
