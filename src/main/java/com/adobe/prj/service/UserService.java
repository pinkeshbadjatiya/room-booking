package com.adobe.prj.service;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adobe.prj.dao.OrderDao;
import com.adobe.prj.dao.ProductDao;
import com.adobe.prj.dao.UserDao;
import com.adobe.prj.entity.Customer;
import com.adobe.prj.entity.Item;
import com.adobe.prj.entity.Order;
import com.adobe.prj.entity.Product;
import com.adobe.prj.entity.User;
import com.adobe.prj.utils.AuthRoles;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	
//	@Transactional
//	public void manageOrder(Order order) {
//
////		userDao.placeOrder(order);
////		List<Item> items = order.getItems();
////		double total = 0.0;
////		for(Item item: items) {
////			Product p = productDao.getProduct(item.getProduct().getId());
////			p.setCount(p.getCount() - item.getQty());
////			total += item.getAmount();
////		}
////		order.setTotal(total);
//		// We made changes to the Product class and still we are not explicitly pushing the changes to the backend,
//		// BECAUSE, the Spring framework maintains dirty checking and ensures syncing of the objects to
//		// to the DB AUTOMAGICALLY!!
//	}
	
	
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
		if (ref_user == null) {
			System.out.println("NULL USER");
			return false;
		}
		if (ref_user.getPassword() != u.getPassword()) {
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
			// TODO: Error, No API key
		}
		User user = userDao.getUserByAPIKey(api_key);
		return user;
	}
	

	public User authenticateUserByAPIKeyAndRole(HttpServletRequest request, AuthRoles roles) {
		System.out.println("inside-auth");
		String api_key = request.getHeader("API-KEY");
		if (api_key == null) {
			// TODO: Error, No API key
		}
		User user = userDao.getUserByAPIKey(api_key);
		System.out.println(user);
		if (!roles.isAuthorized(user.getRole()) && !roles.isAuthorized("*")) {
			System.out.println("Not allowed");
			// TODO: Error, Not allowed
		}
		return user;
	}
}
