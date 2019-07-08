package com.adobe.prj.client;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.adobe.prj.cfg.AppConfig;
import com.adobe.prj.dao.ProductDaoJpaImpl;
import com.adobe.prj.entity.Product;
import com.adobe.prj.service.OrderService;

public class ProductClient {

	public static void main(String[] args) {
		// This line creates a Spring container
		// ctx === this is the container
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		
		// We are telling the spring container to start creating objects
		ctx.register(ProductDaoJpaImpl.class);
		ctx.register(OrderService.class);
		ctx.register(AppConfig.class);
		
		// This tells the Spring to start the wiring
		ctx.refresh();
		
		// By default the service name is the class name with the 1st character as lowercase.
		// We can give a custom name of the service by using the annotation @Service('os')
		OrderService service = ctx.getBean("orderService", OrderService.class);
		Product p = new Product(0, "Testing", 1000.00, "somecategory");
		
		service.addProduct(p);		// For CRUD operations to work, we need to add the @Transactional annotation on top of that method
		p = service.getProduct(2);
		System.out.println(p);
		
		List<Product> products = service.getProducts();
		for(Product prd: products) {
			System.out.println(prd);
		}
		
	}

}
