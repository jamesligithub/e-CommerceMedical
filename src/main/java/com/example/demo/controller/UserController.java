package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.SignInDto;
import com.example.demo.dto.SigninResponseDto;
import com.example.demo.dto.SignupDto;
import com.example.demo.service.UserService;


@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	//here we will create 2 Api
	
	//sign up 
	@PostMapping("/signup")                     
	public ResponseDto signup(@RequestBody  SignupDto  signupDto)    //public class SignupDto {    private String firstName;  private String Lastname;   private String email;  private String password;
	{
		return userService.signUp(signupDto);
	}
	
	//sign in
	@PostMapping ("/signin")
	public SigninResponseDto signIn(@RequestBody SignInDto signInDto)
	{
		return userService.signIn(signInDto);
		
	}

}
