package com.adobe.prj.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adobe.prj.entity.Order;
import com.adobe.prj.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "orders", method = RequestMethod.GET)
	public @ResponseBody List<Order> getOrders() {
		return orderService.getOrders();
	}

	@RequestMapping(value = "orders", method = RequestMethod.POST)
	public ResponseEntity<Order> addOrder(@RequestBody Order o) {
		orderService.manageOrder(o);
		return new ResponseEntity<>(o, HttpStatus.CREATED);
	}
}
