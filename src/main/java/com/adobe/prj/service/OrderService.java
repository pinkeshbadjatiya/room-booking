package com.adobe.prj.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adobe.prj.dao.OrderDao;
import com.adobe.prj.dao.ProductDao;
import com.adobe.prj.entity.Customer;
import com.adobe.prj.entity.Item;
import com.adobe.prj.entity.Order;
import com.adobe.prj.entity.Product;

@Service
public class OrderService {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private OrderDao orderDao;
	
	
	@Transactional
	public void manageOrder(Order order) {

		orderDao.placeOrder(order);
		List<Item> items = order.getItems();
		double total = 0.0;
		for(Item item: items) {
			Product p = productDao.getProduct(item.getProduct().getId());
			p.setCount(p.getCount() - item.getQty());
			total += item.getAmount();
		}
		order.setTotal(total);
		// We made changes to the Product class and still we are not explicitly pushing the changes to the backend,
		// BECAUSE, the Spring framework maintains dirty checking and ensures syncing of the objects to
		// to the DB AUTOMAGICALLY!!
	}
	
	
	public List<Order> getOrders() {
		return orderDao.getOrders();
	}
	
	@Transactional
	public void addProduct(Product p) {
		productDao.addProduct(p);
	}
	
	public List<Product> getProducts() {
		return productDao.getProducts();
	};
	
	public Product getProduct(int id) {
		return productDao.getProduct(id);
	}
}
