package com.example.demo.dto;

public class CheckoutItemDto {
	
	private String productname;
	private int quantiry;
	private double price;
	private long productId;
	private int userId;
	
	//default constructor
	public CheckoutItemDto()
	{
	
	}
 
	
	
	
	public CheckoutItemDto(String productname, int quantiry, double price, long productId, int userId) {
		super();
		this.productname = productname;
		this.quantiry = quantiry;
		this.price = price;
		this.productId = productId;
		this.userId = userId;
	}




	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public int getQuantiry() {
		return quantiry;
	}

	public void setQuantiry(int quantiry) {
		this.quantiry = quantiry;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	

}
