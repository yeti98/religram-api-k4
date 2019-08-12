package com.relipasoft.religram.exception;

import java.util.List;

import org.springframework.validation.FieldError;

public class ValidationException extends RuntimeException {

	private List<FieldError> field;
	
	public ValidationException(List<FieldError> error) {
		this.field = error;
	}

	public List<FieldError> getField() {
		return field;
	}

	public void setField(List<FieldError> field) {
		this.field = field;
	}

	
}
