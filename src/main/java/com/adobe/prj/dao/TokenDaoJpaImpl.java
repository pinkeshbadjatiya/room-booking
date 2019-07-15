package com.adobe.prj.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.adobe.prj.entity.Token;
import com.adobe.prj.entity.User;
import com.adobe.prj.exceptions.InvalidParameterOrMissingValue;

@Repository		// All 'Dao' classes should have repository
public class TokenDaoJpaImpl implements TokenDao {

	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Token getToken(String api_key) {
		Token token = em.find(Token.class, api_key);
		if (token == null) {
			throw new InvalidParameterOrMissingValue("Invalid Parameter or Missing Value. Please supply a valid Token.");
		}
		return token;
	}

	@Override
	@Transactional
	public Token generateToken(User u) {
		// If token is already present, the return the same token
//		String email = u.getEmail();
		String JPQL = "SELECT tok FROM Token tok where tok.user = :user";
		TypedQuery<Token> query = em.createQuery(JPQL, Token.class);
		query.setParameter("user", u);
		 List<Token> query_results = query.getResultList();
		if (query_results.size() > 0) {
			System.out.println("Found token!");
			System.out.println(query_results.get(0));
			return query_results.get(0);
		}
		
		// Generate a new token
		Token token = new Token();
		token.setUser(u);
		token.setRole(u.getRole());
		System.out.println("Token before persist");
		System.out.println(token);
		em.persist(token);
		return token;
	}

	@Override
	public void deleteToken(String api_key) {
//		String JPQL = "SELECT tok FROM Token tok where tok.apiKey=:api_key";
//		List<Token> query_results = em.createQuery(JPQL, Token.class).getResultList();
//		if (query_results.size() <= 0) {
//			// TODO: Error, invalid token
//		} else if (query_results.size() > 1) {
//			// TODO: Error, found multiple tokens
//		}
//		em.remove(query_results.get(0));
//		return query_results.get(0);	
		Token token = em.find(Token.class, api_key);
		if (token == null) {
			throw new InvalidParameterOrMissingValue("Invalid Parameter or Missing Value. Please supply a valid token.");
		}
		em.remove(token);
	}
}
