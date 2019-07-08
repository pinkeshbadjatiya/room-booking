package com.adobe.prj.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.adobe.prj.cfg.AppConfig;
import com.adobe.prj.dao.UserDaoJpaImpl;
import com.adobe.prj.service.UserService;

public class UserClient {

	public static void main(String[] args) {
		// This line creates a Spring container
		// ctx === this is the container
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		
		// We are telling the spring container to start creating objects
		ctx.register(UserDaoJpaImpl.class);
		ctx.register(UserService.class);
		ctx.register(AppConfig.class);
		
		// This tells the Spring to start the wiring
		ctx.refresh();
		
//		Order order = new Order();
//		Customer c = new Customer();
//		c.setEmail("b@adobe.com");
//		order.setCustomer(c);
//		
//		Product p1 = new Product();
//		p1.setId(1);
//		Item i1 = new Item();
//		i1.setProduct(p1);
//		i1.setQty(1);
//		i1.setAmount(p1.getPrice() * i1.getQty());
//
//		Product p2 = new Product();
//		p2.setId(2);
//		Item i2 = new Item();
//		i2.setProduct(p2);
//		i2.setQty(2);
//		i1.setAmount(p2.getPrice() * i2.getQty());
//
//		order.getItems().add(i1);
//		order.getItems().add(i2);
//
//		OrderService service = ctx.getBean("orderService", OrderService.class);
//		service.manageOrder(order);
		
//		UserService service = ctx.getBean("userService", UserService.class);
//		Product p = new User(0, "Testing", 1000.00, "somecategory");
//		
//		service.addProduct(p);		// For CRUD operations to work, we need to add the @Transactional annotation on top of that method
//		p = service.getProduct(2);
//		System.out.println(p);
//		
//		List<Product> products = service.getProducts();
//		for(Product prd: products) {
//			System.out.println(prd);
//		}
	}

}
