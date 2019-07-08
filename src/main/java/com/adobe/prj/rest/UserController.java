package com.adobe.prj.rest;

import java.util.List;
import com.adobe.prj.entity.User;
import com.adobe.prj.service.UserService;

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
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="users", method=RequestMethod.GET)
	public @ResponseBody List<User> getUsers() {
		return userService.getUsers();
	}
	
	@RequestMapping(value="users/{email:.+}", method=RequestMethod.GET)
	public @ResponseBody User getUser(@PathVariable("email") String email) {
		return userService.getUser(email);
	}
	
	// Along with the payload if you want to send a status code, then use ResponseEntity
	
	@RequestMapping(value="users", method=RequestMethod.POST)
	public ResponseEntity<User> addUser(@RequestBody User u) {
		userService.addUser(u);
		return new ResponseEntity<>(u, HttpStatus.CREATED);
	}

	@RequestMapping(value="users", method=RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody User u) {
		userService.updateUser(u);
		return new ResponseEntity<>(u, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="users", method=RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@RequestBody User u) {
		userService.deleteUser(u);
		return new ResponseEntity<>(u, HttpStatus.OK);
	}
	
	
}
