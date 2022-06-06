package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AuthenticationFailException extends IllegalArgumentException{      //Thrown to indicate that a method has been passed an illegal orinappropriate argument.

	public AuthenticationFailException(String msg)    //only has a constructor    super(msg)
	{
		super (msg);
	}
}
////return a erroe message is manged by @ControllerAdvise   use with annotation    @ExceptionalHandler   /// return new ResponseEntity<>(exception.getMessage() , HttpStatus.BAD_REQUEST);
