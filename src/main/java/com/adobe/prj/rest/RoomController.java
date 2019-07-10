package com.adobe.prj.rest;

import java.util.List;

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

@RestController
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@RequestMapping(value="rooms", method=RequestMethod.GET)
	public @ResponseBody List<Room> getRooms() {
		return roomService.getRooms();
	}
	
	@RequestMapping(value="rooms/{id}", method=RequestMethod.GET)
	public @ResponseBody Room getRoom(@PathVariable("id") int id) {
		return roomService.getRoom(id);
	}
	
	// Along with the payload if you want to send a status code, then use ResponseEntity
	
	@RequestMapping(value="rooms", method=RequestMethod.POST)
	public ResponseEntity<Room> addRoom(@RequestBody Room r) {
		roomService.addRoom(r);
		return new ResponseEntity<>(r, HttpStatus.CREATED);
	}

	@RequestMapping(value="rooms", method=RequestMethod.PUT)
	public ResponseEntity<Room> updateRoom(@RequestBody Room r) {
		roomService.updateRoom(r);
		return new ResponseEntity<>(r, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="rooms", method=RequestMethod.DELETE)
	public ResponseEntity<Room> deleteRoom(@RequestBody Room r) {
		roomService.deleteRoom(r);
		return new ResponseEntity<>(r, HttpStatus.OK);
	}
	
	
}
