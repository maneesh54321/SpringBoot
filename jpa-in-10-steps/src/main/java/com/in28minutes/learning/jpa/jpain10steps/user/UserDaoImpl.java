package com.in28minutes.learning.jpa.jpain10steps.user;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.in28minutes.learning.jpa.jpain10steps.entity.User;

@Repository
@Transactional
class UserDaoImpl implements UserDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public long insert(User user) {
		entityManager.persist(user);
		return user.getId();
	}
}
