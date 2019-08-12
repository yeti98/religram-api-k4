package com.relipasoft.religram.data.response;

public class User {

	private int id;
	
	private String username;
	
	private String fullname;
	
	private String email;
	
	private String avatar;
	
	public User() {
		
	}

	public User(String username, String fullname, String email, String avatar) {
		this.username = username;
		this.fullname = fullname;
		this.email = email;
		this.avatar = avatar;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
}
