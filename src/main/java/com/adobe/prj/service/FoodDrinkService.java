package com.adobe.prj.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adobe.prj.dao.FoodDrinkDao;
import com.adobe.prj.entity.FoodDrink;

@Service
public class FoodDrinkService {

	@Autowired
	private FoodDrinkDao foodDrinkDao;
	
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
	
	
	public FoodDrink getFoodDrink(int id) {
		return foodDrinkDao.getFoodDrink(id);
	}

	public List<FoodDrink> getFoodDrinks() {
		return foodDrinkDao.getFoodDrinks();
	}
	
	@Transactional
	public void addFoodDrink(FoodDrink fd) {
		foodDrinkDao.addFoodDrink(fd);
	}
	
	@Transactional
	public void deleteFoodDrink(FoodDrink fd) {
		foodDrinkDao.deleteFoodDrink(fd);
	}

	@Transactional
	public void updateFoodDrink(FoodDrink fd) {
		foodDrinkDao.updateFoodDrink(fd);
	}
}
