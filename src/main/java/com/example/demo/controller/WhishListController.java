package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.apiresponse.ApiResponse;
import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.model.WishList;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.WhishListService;

@RestController
@RequestMapping("/wishlist")
public class WhishListController {
	
	@Autowired
	WhishListService whishListService;///////////////////////////////
	
	@Autowired
	AuthenticationService authenticationService;////////////////////////////
	
	
	//save product as  wishList  item    //save to wishlist API
	@PostMapping("/add")             //   ---------------.com/wishlist/add
	public ResponseEntity<ApiResponse> addToWishList  ( @RequestBody Product product,   @RequestParam("token")String token  )
	{
		
		//authenticate the token( then get the user)
		authenticationService.authenticate(token);
		
		// then we find the user
		User user = authenticationService.getUser(token);
		
		// then  save the item in the wishlist
		WishList wishList= new WishList(user,product);
		//then call servivce  to save it    then call repository to save it 
		whishListService.createWishList(wishList);  //then you create this method method in service 
		
		//then send the response that     it is successful,   HttpStatus.OK
		ApiResponse apiResponse = new ApiResponse( true, "addes to wishlist");
		return  new ResponseEntity<> ( apiResponse, HttpStatus.CREATED);
	}
	
	
	
	
	
///////////////get all wishlist item for a user    //get wishlist items   API///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	@GetMapping("/{token}")   //-------------.com/wishlist/{token}
	public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token)
	{
		//authenticate the token( then get the user)
				authenticationService.authenticate(token);
				
				// then we find the user
				User user = authenticationService.getUser(token);
		
		List<ProductDto> productDtos=  whishListService.getWishListForUser(user);
		                     //a list of productDtos s ss
		return   new ResponseEntity<>(productDtos, HttpStatus.OK);   // wrapp the list  with  HttpStatus.OK   and return it as a response body     response body only used in controller class    in service class we use   return List<productDto>
		
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	

}
