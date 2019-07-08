package com.adobe.prj.dao;

import java.util.List;

import com.adobe.prj.entity.User;

public interface UserDao {
//	void addProduct(Product p);
//	List<Product> getProducts();
//	Product getProduct(int id);
	List<User> getUsers();
	User getUser(String email);
	void addUser(User u);
	void updateUser(User u);
	void deleteUser(User u);
}
