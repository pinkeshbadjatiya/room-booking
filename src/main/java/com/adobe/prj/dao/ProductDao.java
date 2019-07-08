package com.adobe.prj.dao;

import java.util.List;

import com.adobe.prj.entity.Product;

public interface ProductDao {
	void addProduct(Product p);
	List<Product> getProducts();
	Product getProduct(int id);
}
