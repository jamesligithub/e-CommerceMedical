package com.example.demo.apiresponse;

import java.time.LocalDateTime;

public class ApiResponse {
	
	private final boolean success;  // final instance variable can not be modified ?
	private final String message; 
	
	// constructor
	public ApiResponse(boolean success,  String message) {  
		this.success= success;
		this.message = message;
	}

	
	
	public boolean isSuccess() {
		return success;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getTimestamp() {
		return LocalDateTime.now().toString();
	}
	
}
