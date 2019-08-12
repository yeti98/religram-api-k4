package com.relipasoft.religram.data.response;

import java.util.ArrayList;
import java.util.List;

public class ValidationExceptionResponse {

	private int statusCode;
	private List<FieldValidationEntity> message;
	
	public ValidationExceptionResponse() {

	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public List<FieldValidationEntity> getMessage() {
		return message;
	}

	public void setMessage(List<FieldValidationEntity> message) {
		this.message = message;
	}

	public void addFieldValidation(FieldValidationEntity fieldValidation) {
		if(this.message == null) this.message = new ArrayList<FieldValidationEntity>();
		this.message.add(fieldValidation);
	}
	
}
