package com.example.demo.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.SignInDto;
import com.example.demo.dto.SigninResponseDto;
import com.example.demo.dto.SignupDto;
import com.example.demo.exceptions.AuthenticationFailException;
import com.example.demo.exceptions.CustomException;
import com.example.demo.model.AuthenticationToken;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.google.common.base.Objects;




@Service
public class UserService {
	
	
	@Autowired
	UserRepository userRepository;
	
	
	@Autowired 
	AuthenticationService  authenticationService ;///////////////////
	
    @Transactional     //if the AuthenticationToken  is not saved then we will revert the newly created user    because we want both of them  newly created user and AuthenticationToken  to be saved together
	public ResponseDto signUp(SignupDto signupDto) {
		//check  id user is already present
		Optional<User> usermy=Optional.of(userRepository.findByEmail( signupDto.getEmail()));    // if (Objects.nonNull(userRepository.findBYEmail(signupDto.getEmail() )))
		if  (   usermy.isPresent()    )  //if the user is already in database
				{
			       throw new CustomException("user already present");  //it will throw exception will registered at     ExceptionControllerAdvice,   
				}
		
		//hash the password   because you can not store the password in a text format    we need to hasg the pass word  and tehn save it   
		String encryptedpassword = signupDto.getPassword();
		//then we encrypt the pass word 
		try {
		
			encryptedpassword = hashPassword(signupDto.getPassword());  // hashPassword() method throws exception     private String hashPassword(String password) throws NoSuchAlgorithmException  {      
		}
		catch( NoSuchAlgorithmException  e  )
		{
			e.toString();
			e.getMessage();
			e.getStackTrace();
		}                        
		     
		/**
		User user = new User();
		user.setEmail(signupDto.getEmail());
		user.setFirstName(signupDto.getFirstName());
		user.setLastName(signupDto.getLastname());
		user.setPassword(encryptedpassword); //set password you actually save it as an already hashed encrypted password  not the user inputone  
		**/                                                                                         //password  varible
		                                                                                           //hashed password  is save as String password in user class object
		User user= new User(signupDto.getFirstName(),signupDto.getLastname(),signupDto.getEmail(), encryptedpassword) ;
		//save the user ( first save the user   to  table )
		userRepository.save(user);   
		
		//create the token(generated the authentication Token)               //  then later when you sign in   it will find the user and find the token 
		final AuthenticationToken authenticationToken =new AuthenticationToken(user);    //final key word used here    so authenticationToken  can't be changed
		//save the token
		authenticationService.saveConfirmationToken(authenticationToken);
		ResponseDto responseDto = new ResponseDto("success", "User created successfully");
		return responseDto;
	}


	
	
	//////hashPassword method(String password)     ///////method
	private String hashPassword(String password) throws NoSuchAlgorithmException  {
		// TODO Auto-generated method stub
		MessageDigest md = MessageDigest.getInstance("MD5");   //add throws declaration    or surround with try catch
		md.update(password.getBytes() );
		byte[] digest=md.digest();
		String hash= DatatypeConverter.printHexBinary(digest).toUpperCase();
		return hash;
	}




	public SigninResponseDto signIn(SignInDto signInDto) {
		// First we have to find user by email
		Optional<User> optionaluser= Optional.of(userRepository.findByEmail(signInDto.getEmail()));
		      //optionaluser is an optional Object
		if (  ! optionaluser.isPresent() ) //If a value is present, returns true, otherwise false.Returns:true if a value is present, otherwise false
	     {
			throw new AuthenticationFailException("user is not Valid");     //responsebody will say   "user is not Valid"
	     }
		
		                                    //them we have to hash the password
		  //optionaluser.get() return the user
		// then we compare the password in Database 
	try {
		 if (! optionaluser.get().getPassword().equals(hashPassword(signInDto.getPassword())))
			throw new AuthenticationFailException("Wrong Password");       // responsebody will say "wrong password"
	    }
	catch(NoSuchAlgorithmException e)
	{
	    	e.printStackTrace();
	}
		
		
		//if password match//  then retrive the  token Authentication token 
		AuthenticationToken token = authenticationService.getToken(optionaluser.get());
		
	// return response
	                                        //return a string " 3vref32rv2udxuwbccabbc"token
		return new SigninResponseDto("success", token.getToken());
	}

	
}
