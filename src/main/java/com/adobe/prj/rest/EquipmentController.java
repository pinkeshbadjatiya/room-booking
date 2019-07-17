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

import com.adobe.prj.entity.Equipment;
import com.adobe.prj.service.EquipmentService;
import com.adobe.prj.service.UserService;
import com.adobe.prj.utils.AuthRoles;

@RestController
@CrossOrigin(maxAge = 3600)
public class EquipmentController {
	@Autowired
	private EquipmentService equipmentService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="equipments", method=RequestMethod.GET)
	public @ResponseBody List<Equipment> getEquipments(HttpServletRequest request) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"user", "admin"}));		// Authenticates the user based on the API-KEY
		return equipmentService.getEquipments();
	}
	
	@RequestMapping(value="equipments/{id}", method=RequestMethod.GET)
	public @ResponseBody Equipment getEquipment(HttpServletRequest request, @PathVariable("id") int id) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"user", "admin"}));		// Authenticates the user based on the API-KEY
		return equipmentService.getEquipment(id);
	}
	
	// Along with the payload if you want to send a status code, then use ResponseEntity
	
	@RequestMapping(value="equipments", method=RequestMethod.POST)
	public ResponseEntity<Equipment> addEquipment(HttpServletRequest request, @RequestBody Equipment e) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"admin"}));		// Authenticates the user based on the API-KEY
		equipmentService.addEquipment(e);
		return new ResponseEntity<>(e, HttpStatus.CREATED);
	}

	@RequestMapping(value="equipments", method=RequestMethod.PUT)
	public ResponseEntity<Equipment> updateEquipment(HttpServletRequest request, @RequestBody Equipment e) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"admin"}));		// Authenticates the user based on the API-KEY
		equipmentService.updateEquipment(e);
		return new ResponseEntity<>(e, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="equipments/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Equipment> deleteEquipment(HttpServletRequest request, @PathVariable("id") int id) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"admin"}));	// Authenticates the user based on the API-KEY
		Equipment e = equipmentService.getEquipment(id);
		equipmentService.deleteEquipment(e);
		return new ResponseEntity<>(e, HttpStatus.OK);
	}
}
