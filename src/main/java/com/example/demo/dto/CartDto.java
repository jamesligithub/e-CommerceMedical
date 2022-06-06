package com.example.demo.dto;

import java.util.List;

import com.example.demo.model.Product;

public class CartDto {
	private List<CartItemDto> CarItems;////////////public class CartItemDto {id;  product;  quantity;}
	private double totalCost;////////////////
	
	
	public CartDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public List<CartItemDto> getCarItems() {
		return CarItems;
	}


	public void setCarItems(List<CartItemDto> carItems) {
		CarItems = carItems;
	}


	public double getTotalCost() {
		return totalCost;
	}


	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	

}
