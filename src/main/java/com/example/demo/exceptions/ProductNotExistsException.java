package com.example.demo.exceptions;

//Thrown to indicate that a method has been passed an illegal orinappropriate argument
public class ProductNotExistsException extends IllegalArgumentException {
	public ProductNotExistsException(String msg) {
		super(msg); ////////////////////// then we go to @ControllerAdvice  @ExceptionHandler   we register this exception
	}

}