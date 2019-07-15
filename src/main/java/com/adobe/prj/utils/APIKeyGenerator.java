package com.adobe.prj.utils;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class APIKeyGenerator implements IdentifierGenerator{

	@PersistenceContext
	private EntityManager em;
	
    @Override
    public Serializable generate(SessionImplementor session, Object object)
            throws HibernateException {

        String prefix = "APIKEY-";

        String api_key = prefix + RandomStringUtils.random(32, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray());
//		while (true) {
			// TODO: Check if token is already present
//			String JPQL = "SELECT tok FROM Token tok WHERE tok.apiKey=:api_key";
//			List<Token> result_set = em.createQuery(JPQL, Token.class).getResultList();
//			if (result_set.size() == 0) {
//				break;
//			}
//		api_key = prefix + RandomStringUtils.random(32, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray());        		
        System.out.println("API_KEY: " + api_key);
        return api_key;
    }

}