package com.adobe.prj.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.adobe.prj.entity.Token;
import com.adobe.prj.entity.User;
import com.adobe.prj.exceptions.InvalidAPIKey;
import com.adobe.prj.exceptions.InvalidParameterOrMissingValue;

@Repository // All 'Dao' classes should have repository
public class UserDaoJpaImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public User getUserByEmail(String email) {
		// Limitation of find is that it can only find based on the primary key
		// 'find' basically looks at the annotations @Table and @Id to generate
		// the SQL

		User ans = em.find(User.class, email);
		if (ans == null) {
			throw new InvalidParameterOrMissingValue("No user with the given email found.");
		}

		return em.find(User.class, email);
	}

	@Override
	public User getUserByAPIKey(String api_key) {
		String JPQL = "SELECT tok FROM Token tok WHERE tok.apiKey = :api_key";
		TypedQuery<Token> query = em.createQuery(JPQL, Token.class);
		query.setParameter("api_key", api_key);
		List<Token> query_result = query.getResultList();
		if (query_result.size() <= 0) {
			throw new InvalidAPIKey("Invalid API Key. Please supply a valid API key");
		}
		return query_result.get(0).getUser();
	}

	@Override
	public List<User> getUsers() {
		String JPQL = "SELECT u FROM User u";
		TypedQuery<User> query = em.createQuery(JPQL, User.class);
		if (query == null) {
			throw new InvalidParameterOrMissingValue("No user found.");
		}
		return query.getResultList();
	}

	@Override
	@Transactional
	public void updateUser(User u) {
		User _u = em.find(User.class, u.getEmail());
		if (_u == null) {
			throw new InvalidParameterOrMissingValue("No user with the given id found. Hence, updation not possible.");
		}
		_u.setEmail(u.getEmail());
		_u.setName(u.getName());
		_u.setPassword(u.getPassword());
		_u.setPhone(u.getPhone());
		_u.setRole(u.getRole());
		_u.setStatus(u.getStatus());
		em.persist(_u);
	}

	@Override
	@Transactional
	public void deleteUser(User u) {
		User _u = em.find(User.class, u.getEmail());
		if (_u == null) {
			throw new InvalidParameterOrMissingValue("No user with the given id found. Hence, deletion not possible.");
		}
		em.remove(_u);
	}

	@Override
	@Transactional // Ensures the whole function gets executed in an atomic
					// fashion. If not, then it rollsback the whole operation.
	public void addUser(User u) {
		em.persist(u);
	}

}
