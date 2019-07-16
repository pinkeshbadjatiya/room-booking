package com.adobe.prj.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.adobe.prj.entity.Layout;
import com.adobe.prj.entity.User;
import com.adobe.prj.service.LayoutService;
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
public class LayoutController {
	@Autowired
	private LayoutService layoutService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="layouts", method=RequestMethod.GET)
	public @ResponseBody List<Layout> getLayouts(HttpServletRequest request) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"user", "admin"}));		// Authenticates the user based on the API-KEY
		return layoutService.getLayouts();
	}
	
	@RequestMapping(value="layouts/{id}", method=RequestMethod.GET)
	public @ResponseBody Layout getLayout(HttpServletRequest request, @PathVariable("id") int id) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"user", "admin"}));		// Authenticates the user based on the API-KEY
		return layoutService.getLayout(id);
	}
	
	// Along with the payload if you want to send a status code, then use ResponseEntity
	
	@RequestMapping(value="layouts", method=RequestMethod.POST)
	public ResponseEntity<Layout> addLayout(HttpServletRequest request, @RequestBody Layout l) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"admin"}));		// Authenticates the user based on the API-KEY
		layoutService.addLayout(l);
		return new ResponseEntity<>(l, HttpStatus.CREATED);
	}

	@RequestMapping(value="layouts", method=RequestMethod.PUT)
	public ResponseEntity<Layout> updateUser(HttpServletRequest request, @RequestBody Layout l) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"admin"}));		// Authenticates the user based on the API-KEY
		layoutService.updateLayout(l);
		return new ResponseEntity<>(l, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="layouts", method=RequestMethod.DELETE)
	public ResponseEntity<Layout> deleteLayout(HttpServletRequest request, @RequestBody Layout l) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"admin"}));		// Authenticates the user based on the API-KEY
		layoutService.deleteLayout(l);
		return new ResponseEntity<>(l, HttpStatus.OK);
	}
	
	
}
