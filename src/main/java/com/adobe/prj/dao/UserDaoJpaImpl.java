package com.adobe.prj.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.adobe.prj.entity.Product;
import com.adobe.prj.entity.User;

@Repository		// All 'Dao' classes should have repository
public class UserDaoJpaImpl implements UserDao {

	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional		// Ensures the whole function gets executed in an atomic fashion. If not, then it rollsback the whole operation.
	public void addUser(User u) {
		em.persist(u);
	}

	@Override
	public User getUser(String email) {
		// Limitation of find is that it can only find based on the primary key
		// 'find' basically looks at the annotations @Table and @Id to generate the SQL
		return em.find(User.class, email);
	}

	@Override
	public List<User> getUsers() {
		String JPQL = "SELECT u FROM User u";
		TypedQuery<User> query = em.createQuery(JPQL, User.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void updateUser(User u) {
		User _u = em.find(User.class, u.getEmail());
		_u.setEmail(u.getEmail());
		_u.setName(u.getName());
		_u.setPassword(u.getPassword());
		_u.setPhone(u.getPhone());
		_u.setRole(u.getRole());
		_u.setStatus(u.getStatus());
		em.persist(_u);
	}

	@Override
	public void deleteUser(User u) {
		User _u = em.find(User.class, u.getEmail());
		em.remove(_u);
	}

}
