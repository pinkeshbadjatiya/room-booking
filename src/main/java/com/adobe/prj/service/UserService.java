package com.adobe.prj.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adobe.prj.dao.UserDao;
import com.adobe.prj.entity.User;
import com.adobe.prj.exceptions.InvalidId;
import com.adobe.prj.exceptions.InvalidParameterOrMissingValue;
import com.adobe.prj.exceptions.NotAuthorized;
import com.adobe.prj.utils.AuthRoles;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	
	public User getUserByEmail(String email) {
		System.out.println(email);
		return userDao.getUserByEmail(email);
	}

	public User getUserByAPIKey(String api_key) {
		System.out.println(api_key);
		return userDao.getUserByAPIKey(api_key);
	}

	public List<User> getUsers() {
		return userDao.getUsers();
	}

	public Boolean isValidUser(User u) {
		System.out.println(u);
		User ref_user = userDao.getUserByEmail(u.getEmail());
		System.out.println(ref_user);
		if (ref_user == null) {
			System.out.println("NULL USER");
			return false;
		}
		if (!ref_user.getPassword().equals(u.getPassword())) {
			System.out.println(ref_user.getPassword());
			System.out.println(u.getPassword());
			return false;
		}
		return true;
	}

	@Transactional
	public void addUser(User u) {
		userDao.addUser(u);
	}

	@Transactional
	public void deleteUser(User u) {
		userDao.deleteUser(u);
	}

	@Transactional
	public void updateUser(User u) {
		userDao.updateUser(u);
	}

	public User authenticateUserByAPIKey(HttpServletRequest request) {
		String api_key = request.getHeader("API-KEY");
		if (api_key == null) {
			throw new InvalidId("Invalid API-KEY. Please supply a valid Id.");
		}
		User user = userDao.getUserByAPIKey(api_key);
		return user;
	}

	public User authenticateUserByAPIKeyAndRole(HttpServletRequest request, AuthRoles roles) {
		System.out.println("inside-auth");
		String api_key = request.getHeader("API-KEY");
		System.out.println(api_key);
		if (api_key == null) {
			throw new InvalidId("Invalid API-KEY. Please supply a valid Id.");
		}
		User user = userDao.getUserByAPIKey(api_key);
		System.out.println(user);
		if (!roles.isAuthorized(user.getRole()) && !roles.isAuthorized("*")) {
			System.out.println("Not allowed");
			// Error: Not allowed
			throw new NotAuthorized("Not allowed");
		}
		return user;
	}
}
