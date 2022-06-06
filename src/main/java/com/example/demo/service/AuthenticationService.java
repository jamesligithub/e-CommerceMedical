package com.example.demo.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.AuthenticationFailException;
import com.example.demo.model.AuthenticationToken;
import com.example.demo.model.User;
import com.example.demo.repository.TokenRepository;



@Service
public class AuthenticationService {
	
	
	@Autowired
	TokenRepository  tokenRepository;

	public void saveConfirmationToken(AuthenticationToken authenticationToken) {
		// TODO Auto-generated method stub
		tokenRepository.save(authenticationToken);
	}

	
	public AuthenticationToken getToken(User user) {
		// TODO Auto-generated method stub
		
		return tokenRepository.findByUser(user);
	}

	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	//getUser method     //create a method to get the user from the token 
	public User getUser(String token) {
		                                             // important step here!!!!!!!!!!!!
		final AuthenticationToken authenticationtoken= tokenRepository.findByToken(token);  //use token to find the  authenticationtoken object  //final key word is used   so can not change it later
		
		if (Objects.isNull(authenticationtoken))
		{
			return null;
		}
		else
		{
			return authenticationtoken.getUser();///////////the  through aithentication object to find  the usetr  /////get user
		}
	}
	
	    
	public void authenticate(String token) /////very important used in cartcontroller too!!!!
	{
		//null check
		if (Objects.isNull(token))
		{
			//if is null   then throw an exception
			throw new AuthenticationFailException("token not present");
		}
		
		if (Objects.isNull( getUser(token) )   )  //getUser(token)  method   see above!!!!!!!
		{
			throw new AuthenticationFailException("token not valid, no user found with that token");
		}
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
}

