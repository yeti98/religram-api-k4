package com.relipasoft.religram.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.relipasoft.religram.entity.UserDB;
import com.relipasoft.religram.utils.EncrytedPasswordUtils;

public class SignupRequest {

	@NotNull(message="must not be blank")
	@Email(message="must be a well-formed email address")
	@Size(min=6, max=32)
	private String email;
	
	@NotNull(message="must not be blank")
	@Size(min=1, max=32)
	private String fullname;

	@NotNull(message="must not be blank")
	@Size(min=1, max=32)
	@Pattern(regexp="^[a-z0-9]*$", message="username must contain only character [0-9] and [a-z]")
	private String username;
	
	@NotNull(message="must not be blank")
	@Size(min=1, max=32)
	private String password;

	public SignupRequest() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserDB mapUserDB() {
		UserDB userDB = new UserDB();
		userDB.setId(0);
		userDB.setUsername(this.username);
		userDB.setPassword(EncrytedPasswordUtils.encrytePassword(this.password));
		userDB.setFullname(this.fullname);
		userDB.setEmail(this.email);
		return userDB;
	}
	
	
}
