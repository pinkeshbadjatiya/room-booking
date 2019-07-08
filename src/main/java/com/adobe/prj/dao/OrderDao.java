package com.adobe.prj.dao;

import java.util.List;

import com.adobe.prj.entity.Order;

public interface OrderDao {

	void placeOrder(Order o);
	List<Order> getOrders();
}
