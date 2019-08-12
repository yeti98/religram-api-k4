package com.relipasoft.religram.exception.handler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.relipasoft.religram.data.response.EntityExceptionResponse;
import com.relipasoft.religram.data.response.FieldValidationEntity;
import com.relipasoft.religram.data.response.ValidationExceptionResponse;
import com.relipasoft.religram.exception.EmailExistedException;
import com.relipasoft.religram.exception.UsernameExistedException;
import com.relipasoft.religram.exception.ValidationException;

@ControllerAdvice
public class SignUpExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ValidationExceptionResponse> handleException(ValidationException exc) {

		List<FieldError> errors = exc.getField();
		ValidationExceptionResponse response = new ValidationExceptionResponse();
		response.setStatusCode(400);
		
		for (FieldError error : errors ) {
			FieldValidationEntity field = new FieldValidationEntity();
			field.setKey(error.getField());
			field.setMessage(error.getDefaultMessage());
			response.addFieldValidation(field);
	    }
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<EntityExceptionResponse> handleException(UsernameExistedException exc) {

		EntityExceptionResponse response = new EntityExceptionResponse();
		
		response.setMessage(exc.getMessage());
		response.setStatusCode(409);
		
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler
	public ResponseEntity<EntityExceptionResponse> handleException(EmailExistedException exc) {

		EntityExceptionResponse response = new EntityExceptionResponse();
		
		response.setMessage(exc.getMessage());
		response.setStatusCode(409);
		
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}
	
}
