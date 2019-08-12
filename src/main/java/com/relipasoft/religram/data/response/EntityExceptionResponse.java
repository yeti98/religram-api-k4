package com.relipasoft.religram.data.response;

public class EntityExceptionResponse {

	private int statusCode;
	private String message;
	
	public EntityExceptionResponse(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}
	
	public EntityExceptionResponse() {

	}

	public int getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
}
