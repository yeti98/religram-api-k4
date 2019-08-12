package com.relipasoft.religram.dao;

import java.util.List;

import com.relipasoft.religram.entity.UserDB;

public interface UserDAO {

	public List<UserDB> findAll();
	
	public UserDB findById(int theId);
	
	public void save(UserDB theUser);
	
	public void deleteById(int theId);
	
	public List<UserDB> findByEmail(String email);
	
	public List<UserDB> findByUsername(String username);
	
}
