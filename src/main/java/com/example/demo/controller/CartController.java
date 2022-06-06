package com.example.demo.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.apiresponse.ApiResponse;
import com.example.demo.dto.AddToCartDto;
import com.example.demo.dto.CartDto;
import com.example.demo.dto.CartItemDto;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/cart")
public class CartController{
	
	
	@Autowired
	private CartService cartService;
	@Autowired 
	private AuthenticationService authenticationService;
	

	// post cart api                                                     // productId is used , we extract productId front AddTOCartDto
	@PostMapping("/add")                                                //AddToCartDto(Integer id,  Integer productId,  Integer quantity)                      
	public ResponseEntity<ApiResponse>addToCart(@RequestBody AddToCartDto addToCartDto ,@RequestParam("token") String token)
	{                                                                                                           //use token get user
		//authenticate the token 
		authenticationService.authenticate(token);
		
		//after the token is valid  then ---- find the user!
		User user= authenticationService.getUser(token);
		
	   cartService.addToCart(addToCartDto, user);////////call this cartervice dummy method , then call repository =save method
	
	   return    new ResponseEntity<> ( new ApiResponse (true, "Added to Cart")  ,   HttpStatus.CREATED);


	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	//get all cart items for a user   showing list of items  and also shoing the total price for all items 
	@GetMapping("/")   //http;//localhost:8080/cart/ ?token=eb744486-8696-86bc-8bfd2cf7dd48
	public ResponseEntity<CartDto> getCartItems(@RequestParam("token")String token)
	{
	     //authenticate the token 
			authenticationService.authenticate(token);
			
			//after the token is valid  then ---- find the user!
			User user= authenticationService.getUser(token);
			//get cart items
			CartDto cartDto = cartService.listCartItems(user);//////////////return a cartDto///getCartItems===listCartItem
			return  new ResponseEntity<> (cartDto , HttpStatus.OK);
	
	}
	/**
	 * public class CartDto {
	private List<CartItemDto> CarItems;////////////public class CartItemDto {id;  product;  quantity;}
	private double totalCoat;////////////////
	 *
	 * 
	 *public class CartItemDto {
	private Integer id;
	private Integer quantity; 
	private Product product;//  should use ProductDto   not the model 
	 * 
	 * 
	 * public Cart (Integer id,  Product product,  User user,   int quantity,    Date createDate, ) 
	 */
////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	
	// delete a cart item for a user
	@DeleteMapping("/delete/{cartItemId}")   //Request URl  http:// localhost:8080/cart/delete/19 ? token=78cb-6975-42abf2eba4
	public ResponseEntity<ApiResponse>  deleteCartItem(@PathVariable("cartItemId") Integer itemId,  @RequestParam("token") String token)
	{
		 //authenticate the token 
		authenticationService.authenticate(token);
		
		//after the token is valid  then ---- find the user!
		User user= authenticationService.getUser(token); ////////////////get the user from token
		
		cartService.deleteCartItem(itemId, user); ////////////
		
		return   new ResponseEntity<>( new  ApiResponse(true, "Item has been removed/deleted")     , HttpStatus.OK);
		
	}
	
}


