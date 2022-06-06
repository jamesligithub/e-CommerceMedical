package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.demo.dto.AddToCartDto;
import com.example.demo.dto.CartDto;
import com.example.demo.dto.CartItemDto;
import com.example.demo.exceptions.CustomException;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.CartRepository;

@Service
public class CartService {
     
	@Autowired
	CartRepository cartRepository;
	@Autowired
	ProductService productService;
	
	
	
	public void addToCart(AddToCartDto addToCartDto, User user) {
		// TODO Auto-generated method stub
		//validate if the product id is valid 
		Product product =productService.findById( addToCartDto.getProductId() );
		//public Cart(Integer id, Date createDate, Product product, User user, int quantity) // cart   product   user  quantilty  created_date
		Cart cart = new Cart();
		cart.setProduct(product);///cart's first inportant element/
		cart.setUser(user);///cart;s second important element
		cart.setQuantity( addToCartDto.getQuantity()); // addToCartDto.getQuantity()-----extract quantity front addToCartDto
		cart.setCreateDate( new Date() );
		// save the cart
		cartRepository.save(cart);
		
		
		
	}

	/**
public class CartDto {
	private List<CartItemDto> CarItems;////////////public class CartItemDto {id;  product;  quantity;}
	private double totalCoat;////////////////
	 *
	 * 
public class CartItemDto {
	private Integer id;
	private Integer quantity;
	private Product product;//  should use ProductDto   not the model 
	 * 
	 * 
	 * public Cart (Integer id,  Product product,  User user,   int quantity,    Date createDate, ) 
	 */
	
    public CartDto listCartItems(User user) {
		// TODO Auto-generated method stub
	  List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);//return the cart object---public Cart(Integer id,Product product, User user, int quantity Date createDate, ) {
		// then transform the cartList : List<Cart>  to   List<CartItem>    in short words : List<Cart>  to   List<CartItem> 
		List<CartItemDto> cartItems = new ArrayList<>();
		
		double totalCost=0;
		for(Cart cart : cartList) // we change each Cart  to  cartItemDto
		{  //CartItemDto cartItemDto = new CartItemDto(cart);  // this line of code cn replace next four line // this used a CartItemDto class's customized constructor  take in a cart as premeter
			CartItemDto cartItemDto= new CartItemDto();
			cartItemDto.setId(cart.getId());
			cartItemDto.setProduct(cart.getProduct());
			cartItemDto.setQuantity(cart.getQuantity());
			
			cartItems.add(cartItemDto);
			totalCost = totalCost+ (cart.getProduct().getPrice()* cart.getQuantity());
		}
		// Build cartDto  thefinal package whish is going to be send out to front end
		CartDto cartDto= new CartDto();///////return this//////////////
		cartDto.setCarItems(cartItems);
		cartDto.setTotalCost(totalCost);
		return cartDto;
	}

	public void deleteCartItem(Integer cartItemId , User user) {
		// TODO Auto-generated method stub
		Optional<Cart> optionalCart = cartRepository.findById(cartItemId);
		if (optionalCart.isEmpty())  ///If a value is not present, returns true, otherwise false.   Returns:true if a value is not present, otherwise false
		{
			throw   new CustomException("Cart item id is invalid" + cartItemId);
		}
		
		Cart cart = optionalCart.get();/////////////////
		if (cart.getUser() != user)
		{
			throw  new CustomException (" cart item does not lelong to user : " + cartItemId);
		}
		cartRepository.delete(cart);
		//cartRepository.deleteById(cartItemId);
	}

}
