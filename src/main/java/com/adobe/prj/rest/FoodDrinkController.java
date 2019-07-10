package com.adobe.prj.rest;

import java.util.List;

import com.adobe.prj.entity.FoodDrink;
import com.adobe.prj.service.FoodDrinkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FoodDrinkController {
	@Autowired
	private FoodDrinkService foodDrinkService;
	
	@RequestMapping(value="fooddrinks", method=RequestMethod.GET)
	public @ResponseBody List<FoodDrink> getFoodDrinks() {
		return foodDrinkService.getFoodDrinks();
	}
	
	@RequestMapping(value="fooddrinks/{id}", method=RequestMethod.GET)
	public @ResponseBody FoodDrink getFoodDrink(@PathVariable("id") int id) {
		return foodDrinkService.getFoodDrink(id);
	}
	
	// Along with the payload if you want to send a status code, then use ResponseEntity
	
	@RequestMapping(value="fooddrinks", method=RequestMethod.POST)
	public ResponseEntity<FoodDrink> addFoodDrink(@RequestBody FoodDrink fd) {
		foodDrinkService.addFoodDrink(fd);
		return new ResponseEntity<>(fd, HttpStatus.CREATED);
	}

	@RequestMapping(value="fooddrinks", method=RequestMethod.PUT)
	public ResponseEntity<FoodDrink> updateFoodDrink(@RequestBody FoodDrink fd) {
		foodDrinkService.updateFoodDrink(fd);
		return new ResponseEntity<>(fd, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="fooddrinks", method=RequestMethod.DELETE)
	public ResponseEntity<FoodDrink> deleteFoodDrink(@RequestBody FoodDrink fd) {
		foodDrinkService.deleteFoodDrink(fd);
		return new ResponseEntity<>(fd, HttpStatus.OK);
	}
}
