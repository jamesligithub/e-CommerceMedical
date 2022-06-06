package com.example.demo.dto;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;

public class CartItemDto {
	
	private Integer id;
	private Integer quantity;
	private Product product;//  should use ProductDto   not the model
	
	
	public CartItemDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	////////constructor////////use at cartservice to trans form --List of cart   to --list of CartItemDto
	public CartItemDto(Cart cart) {
		this.id= cart.getId();
		this.product= cart.getProduct();
		this.quantity= cart.getQuantity();
		
		
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}

	
	
	
	
	
}
