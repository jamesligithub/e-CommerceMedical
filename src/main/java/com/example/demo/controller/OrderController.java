package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.apiresponse.ApiResponse;
import com.example.demo.apiresponse.StripeResponse;
import com.example.demo.dto.CheckoutItemDto;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private OrderService orderService;

	
	//stripe session checkout api
	
	@PostMapping("/create-checkout-session")
	public ResponseEntity<ApiResponse> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtoList) throws StripeException //import com.stripe.net.StripeResponse;
	{   //import com.stripe.model.checkout.Session;
		Session session = orderService.createSession(checkoutItemDtoList); 
		StripeResponse  stripeResponse = new StripeResponse(session.getId());
		return   new ResponseEntity<>(stripeResponse, HttpStatus.OK);
	}
	
	
	
}
