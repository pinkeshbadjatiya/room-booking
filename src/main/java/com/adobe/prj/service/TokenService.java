package com.adobe.prj.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adobe.prj.dao.TokenDao;
import com.adobe.prj.dao.UserDao;
import com.adobe.prj.entity.Token;
import com.adobe.prj.entity.User;

@Service
public class TokenService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private TokenDao tokenDao;
	
	public Token getToken(String api_key) {
		System.out.println(">> API_KEY: " + api_key);
		return tokenDao.getToken(api_key);
	}

	@Transactional
	public Token generateToken(User u) {
		return tokenDao.generateToken(u);
	}
	
	@Transactional
	public void deleteToken(String api_key) {
		tokenDao.deleteToken(api_key);
	}
}
