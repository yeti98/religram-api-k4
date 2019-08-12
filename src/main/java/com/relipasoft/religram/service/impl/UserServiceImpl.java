package com.relipasoft.religram.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.relipasoft.religram.dao.UserDAO;
import com.relipasoft.religram.entity.UserDB;
import com.relipasoft.religram.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserDAO userDAO;
	
	@Autowired
	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional
	public List<UserDB> findAll() {
		return userDAO.findAll();
	}

	@Override
	@Transactional
	public UserDB findById(int theId) {
		return userDAO.findById(theId);
	}

	@Override
	@Transactional
	public void save(UserDB theUser) {
		userDAO.save(theUser);
	}

	@Override
	@Transactional
	public void deleteById(int theId) {
		userDAO.deleteById(theId);
	}

	@Override
	@Transactional
	public List<UserDB> findByEmail(String email) {
		return userDAO.findByEmail(email);
	}
	
	@Override
	@Transactional
	public List<UserDB> findByUsername(String username) {
		return userDAO.findByUsername(username);
	}

	@Override
	public String getToken(UserDB user) {
		return TokenAuthenticationService.getToken(user);
	}	

}
