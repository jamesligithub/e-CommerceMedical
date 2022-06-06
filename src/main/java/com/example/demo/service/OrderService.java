package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.example.demo.dto.CheckoutItemDto;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionCreateParams.LineItem;
import com.stripe.param.checkout.SessionCreateParams.LineItem.Builder;

@Service
public class OrderService {

	
	@Value("${BASE_URL}")  //get the value from applicatuon properties  to the variable below
	private String baseURL; //BASE_URL = http://localhost:8081/
	
	@Value("{STRIPE_SECRET_KEY}")//get the value from applicatuon properties  to the variable below
	private String apiKey;  //STRIPE_SECRET_KEY= sk_test_51L7PjgAuaYDAdPUleFCChuTWinJqaVfsq01pRCls78HUiNNSidXM6L74U0U9DHvMkeO1hxIJ22u885LxzwghI2nm008FqWnwER
	
	
	public Session createSession(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {
		
		//success url   and   failure url
		String successURL= baseURL +"payment/success";
		String failureURl = baseURL + "payment/failed";
//this is globle object
		Stripe.apiKey=apiKey;
		
		// we will create these pages in our front end app
		List<SessionCreateParams.LineItem> sessionItemList = new ArrayList<>();  //sessionItemlist is a list of  LineItem //List<SessionCreateParams.LineItem> sessionItemList 
		
		// create a LineItem for each chechoutItemDto
		for(CheckoutItemDto checkoutItemDto :  checkoutItemDtoList)
		{
			sessionItemList.add(createSeesionLineItem(checkoutItemDto));////////////////see the belwo define the method  for specific what is going on
		}
		
		SessionCreateParams params = SessionCreateParams.builder()
				.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
				 .setMode(SessionCreateParams.Mode.PAYMENT)
				 .setCancelUrl(failureURl)
				 .setSuccessUrl(successURL)
				 .addAllLineItem(sessionItemList) ////
				 .build();
		return Session.create(params);///throws StripeException     // in the classs that calles thid method   the parent class is also throws same exception
		// TODO Auto-generated method stub
          
	}

	//   method -------createSeesionLineItem(checkoutItemDto)-----method
	private SessionCreateParams.LineItem createSeesionLineItem(CheckoutItemDto checkoutItemDto) {
		// TODO Auto-generated method stub
		return SessionCreateParams.LineItem.builder()
				.setPriceData(createPriceData(checkoutItemDto)////////////////////tricky
				.setQuantity(Long.parseLong( String.valueOf (checkoutItemDto.getQuantiry()) )  )  // first  String.value of (3)  change int to string    then use  Long.parseLong  change  string to Long object
				.build();
	}

	private SessionCreateParams.LineItem.PriceData createPriceData(CheckoutItemDto checkoutItemDto) {
		// TODO Auto-generated method stub
		
		return SessionCreateParams.LineItem.PriceData.builder()  ///  tricky
				.setCurrency("usd")
				setUnitAmount((long)checkoutItemDto.getPrice()*100)
				setProductData( 
						        SessionCreateParams.LineItem.PriceData.ProductData.builder()
						                                                          .setName(checkoutItemDto.getProductname())
						                                                          .build()
						       ).build();
	}
	
	/**
	 * public CheckoutItemDto(String productname,  int quantiry,  double price,  long productId,  int userId) 
	 */
	
	

}
