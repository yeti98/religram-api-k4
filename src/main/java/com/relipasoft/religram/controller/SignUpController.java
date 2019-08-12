package com.relipasoft.religram.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.relipasoft.religram.data.response.LoginResponse;
import com.relipasoft.religram.data.response.User;
import com.relipasoft.religram.entity.UserDB;
import com.relipasoft.religram.exception.EmailExistedException;
import com.relipasoft.religram.exception.UsernameExistedException;
import com.relipasoft.religram.exception.ValidationException;
import com.relipasoft.religram.request.SignupRequest;
import com.relipasoft.religram.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class SignUpController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/signup")
	public LoginResponse addUser(
			@Valid @RequestBody SignupRequest signupRequest, BindingResult theBindingResult) {
		
		if(theBindingResult.hasErrors()) {
			List<FieldError> errors = theBindingResult.getFieldErrors();
			throw new ValidationException(errors);
		} else {
			
			if(!userService.findByUsername(signupRequest.getUsername()).isEmpty()) {
				throw new UsernameExistedException("username existed:" + signupRequest.getUsername());
			}
			
			if(!userService.findByEmail(signupRequest.getEmail()).isEmpty()) {
				throw new EmailExistedException("email existed:" + signupRequest.getEmail());
			}
			
			UserDB userDB = signupRequest.mapUserDB();
			
			userService.save(userDB);
			
			User user = userDB.mapUser();
			
			String token = userService.getToken(userDB);
			
			return new LoginResponse(user, token);
		}
		
	}
	
}
