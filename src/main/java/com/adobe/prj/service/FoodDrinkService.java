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
