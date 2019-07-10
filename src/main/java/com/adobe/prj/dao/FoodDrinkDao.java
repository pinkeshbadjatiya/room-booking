package com.adobe.prj.dao;

import java.util.List;

import com.adobe.prj.entity.FoodDrink;

public interface FoodDrinkDao {
	// void addProduct(Product p);
	// List<Product> getProducts();
	// Product getProduct(int id);
	List<FoodDrink> getFoodDrinks();

	FoodDrink getFoodDrink(int id);

	void addFoodDrink(FoodDrink fd);

	void updateFoodDrink(FoodDrink fd);

	void deleteFoodDrink(FoodDrink fd);
	
}
