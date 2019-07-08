package com.adobe.prj.service;

import java.util.List;

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
	
	
	public User getUser(String email) {
		System.out.println(email);
		return userDao.getUser(email);
	}

	public List<User> getUsers() {
		return userDao.getUsers();
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
}
