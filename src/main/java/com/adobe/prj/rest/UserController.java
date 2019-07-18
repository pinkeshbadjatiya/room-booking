package com.adobe.prj.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.adobe.prj.entity.User;
import com.adobe.prj.exceptions.NotAuthorized;
import com.adobe.prj.service.UserService;
import com.adobe.prj.utils.AuthRoles;  

@CrossOrigin(maxAge = 3600)
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="users", method=RequestMethod.GET)
	public @ResponseBody List<User> getUsers(HttpServletRequest request) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"admin"}));		// Authenticates the user based on the API-KEY
		return userService.getUsers();
	}
	
	@RequestMapping(value="users/{email:.+}", method=RequestMethod.GET)
	public @ResponseBody User getUser(HttpServletRequest request, @PathVariable("email") String email) {
		User user = userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"admin", "user"}));		// Authenticates the user based on the API-KEY
		if (user.getEmail() != email && user.getRole() != "admin")
			throw new NotAuthorized("You are not authorized to access this value.");
		return userService.getUserByEmail(email);
	}
	
	// Along with the payload if you want to send a status code, then use ResponseEntity
	
	@RequestMapping(value="users", method=RequestMethod.POST)
	public ResponseEntity<User> addUser(HttpServletRequest request, @RequestBody User u) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"admin"}));		// Authenticates the user based on the API-KEY
		userService.addUser(u);
		return new ResponseEntity<>(u, HttpStatus.CREATED);
	}

	@RequestMapping(value="users", method=RequestMethod.PUT)
	public ResponseEntity<User> updateUser(HttpServletRequest request, @RequestBody User u) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"admin"}));		// Authenticates the user based on the API-KEY
		userService.updateUser(u);
		return new ResponseEntity<>(u, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="users/{email:.+}", method=RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(HttpServletRequest request, @PathVariable("email") String email) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"admin"}));		// Authenticates the user based on the API-KEY
		User u = userService.getUserByEmail(email);
		userService.deleteUser(u);
		return new ResponseEntity<>(u, HttpStatus.OK);
	}
	
}
