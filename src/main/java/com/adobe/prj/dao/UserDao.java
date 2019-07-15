package com.adobe.prj.dao;

import java.util.List;

import com.adobe.prj.entity.User;

public interface UserDao {
//	void addProduct(Product p);
//	List<Product> getProducts();
//	Product getProduct(int id);
	List<User> getUsers();
	User getUserByEmail(String email);
	User getUserByAPIKey(String api_key);
	void addUser(User u);
	void updateUser(User u);
	void deleteUser(User u);
}
