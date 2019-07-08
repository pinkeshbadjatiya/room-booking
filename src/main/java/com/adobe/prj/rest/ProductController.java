package com.adobe.prj.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adobe.prj.entity.Product;
import com.adobe.prj.service.OrderService;

@RestController
public class ProductController {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="products", method=RequestMethod.GET)
	public @ResponseBody List<Product> getProducts() {
		return orderService.getProducts();
	}
	
	@RequestMapping(value="products/{id}", method=RequestMethod.GET)
	public @ResponseBody Product getProduct(@PathVariable("id") int id) {
		return orderService.getProduct(id);
	}
	
	// Along with the payload if you want to send a status code, then use ResponseEntity
	
	@RequestMapping(value="products", method=RequestMethod.POST)
	public ResponseEntity<Product> addProduct(@RequestBody Product p) {
		orderService.addProduct(p);
		return new ResponseEntity<>(p, HttpStatus.CREATED);
	}

}
