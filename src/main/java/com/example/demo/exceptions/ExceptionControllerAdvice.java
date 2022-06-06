package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice   ////////////////////////     @ControllerAdvise      use with annotation ExceptionalHandler
public class ExceptionControllerAdvice {
	
	@ExceptionHandler(value = CustomException.class)////////////////////////
	public final  ResponseEntity<String>   handleCustomException(CustomException exception)
	{
			
			return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
	
	
	
	@ExceptionHandler(value= AuthenticationFailException.class)///////////////
	public final  ResponseEntity<String>  handleAutenticationFailException(AuthenticationFailException exception)
	{
		return new ResponseEntity<>(exception.getMessage() , HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(value =ProductNotExistsException.class)
	public final ResponseEntity<String>  handleProductNotExistsException(ProductNotExistsException  exception)
	{
		return     new ResponseEntity<> (exception.getMessage(), HttpStatus.BAD_REQUEST);
		
	}

}


