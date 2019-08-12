package com.relipasoft.religram.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.relipasoft.religram.dao.UserDAO;
import com.relipasoft.religram.entity.UserDB;

@Repository
public class UserDAOHibernateImpl implements UserDAO {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<UserDB> findAll() {
		
		Session currentSession = entityManager.unwrap(Session.class);

		Query<UserDB> theQuery = currentSession.createQuery("from UserDB", UserDB.class);

		List<UserDB> users = theQuery.getResultList();
		
		return users;
	}

	@Override
	public UserDB findById(int theId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		UserDB theUser = currentSession.get(UserDB.class, theId);
		
		return theUser;
	}

	@Override
	public void save(UserDB theUser) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(theUser);
	}

	@Override
	public void deleteById(int theId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<UserDB> theQuery = currentSession.createQuery(
				"delete from UserDB where id:=userId", UserDB.class);
		
		theQuery.setParameter("userId", theId);
		
		theQuery.executeUpdate();
		
	}

	@Override
	public List<UserDB> findByEmail(String email) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<UserDB> theQuery = currentSession.createQuery(
				"from UserDB where email='"+ email + "'", UserDB.class);
		
		List<UserDB> theUser = theQuery.getResultList();
		return theUser;
	}

	@Override
	public List<UserDB> findByUsername(String username) {

		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<UserDB> theQuery = currentSession.createQuery(
				"from UserDB where username='"+ username + "'", UserDB.class);
		
		List<UserDB> theUser = theQuery.getResultList();
		return theUser;
	}
	

}
