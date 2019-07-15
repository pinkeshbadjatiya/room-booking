package com.adobe.prj.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adobe.prj.entity.Room;
import com.adobe.prj.service.RoomService;
import com.adobe.prj.service.UserService;
import com.adobe.prj.utils.AuthRoles;

@RestController
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="rooms", method=RequestMethod.GET)
	public @ResponseBody List<Room> getRooms(HttpServletRequest request) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"user", "admin"}));		// Authenticates the user based on the API-KEY		
		return roomService.getRooms();
	}
	
	@RequestMapping(value="rooms/{id}", method=RequestMethod.GET)
	public @ResponseBody Room getRoom(HttpServletRequest request, @PathVariable("id") int id) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"user", "admin"}));		// Authenticates the user based on the API-KEY		
		return roomService.getRoom(id);
	}
	
	// Along with the payload if you want to send a status code, then use ResponseEntity
	
	@RequestMapping(value="rooms", method=RequestMethod.POST)
	public ResponseEntity<Room> addRoom(HttpServletRequest request, @RequestBody Room r) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"admin"}));		// Authenticates the user based on the API-KEY		
		roomService.addRoom(r);
		return new ResponseEntity<>(r, HttpStatus.CREATED);
	}

	@RequestMapping(value="rooms", method=RequestMethod.PUT)
	public ResponseEntity<Room> updateRoom(HttpServletRequest request, @RequestBody Room r) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"admin"}));		// Authenticates the user based on the API-KEY		
		roomService.updateRoom(r);
		return new ResponseEntity<>(r, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="rooms", method=RequestMethod.DELETE)
	public ResponseEntity<Room> deleteRoom(HttpServletRequest request, @RequestBody Room r) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"admin"}));		// Authenticates the user based on the API-KEY		
		roomService.deleteRoom(r);
		return new ResponseEntity<>(r, HttpStatus.OK);
	}
	
	
}
