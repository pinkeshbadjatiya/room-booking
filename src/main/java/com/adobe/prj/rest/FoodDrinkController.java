package com.adobe.prj.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.adobe.prj.entity.FoodDrink;
import com.adobe.prj.service.FoodDrinkService;
import com.adobe.prj.service.UserService;
import com.adobe.prj.utils.AuthRoles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
public class FoodDrinkController {
	@Autowired
	private FoodDrinkService foodDrinkService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="fooddrinks", method=RequestMethod.GET)
	public @ResponseBody List<FoodDrink> getFoodDrinks(HttpServletRequest request) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"user", "admin"}));		// Authenticates the user based on the API-KEY
		return foodDrinkService.getFoodDrinks();
	}
	
	@RequestMapping(value="fooddrinks/{id}", method=RequestMethod.GET)
	public @ResponseBody FoodDrink getFoodDrink(HttpServletRequest request, @PathVariable("id") int id) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"user", "admin"}));		// Authenticates the user based on the API-KEY
		return foodDrinkService.getFoodDrink(id);
	}
	
	// Along with the payload if you want to send a status code, then use ResponseEntity
	
	@RequestMapping(value="fooddrinks", method=RequestMethod.POST)
	public ResponseEntity<FoodDrink> addFoodDrink(HttpServletRequest request, @RequestBody FoodDrink fd) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"admin"}));		// Authenticates the user based on the API-KEY
		foodDrinkService.addFoodDrink(fd);
		return new ResponseEntity<>(fd, HttpStatus.CREATED);
	}

	@RequestMapping(value="fooddrinks", method=RequestMethod.PUT)
	public ResponseEntity<FoodDrink> updateFoodDrink(HttpServletRequest request, @RequestBody FoodDrink fd) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"admin"}));		// Authenticates the user based on the API-KEY
		foodDrinkService.updateFoodDrink(fd);
		return new ResponseEntity<>(fd, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="fooddrinks/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<FoodDrink> deleteFoodDrink(HttpServletRequest request, @PathVariable("id") int id) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"admin"}));		// Authenticates the user based on the API-KEY
		FoodDrink fd = foodDrinkService.getFoodDrink(id);
		foodDrinkService.deleteFoodDrink(fd);
		return new ResponseEntity<>(fd, HttpStatus.OK);
	}
}
