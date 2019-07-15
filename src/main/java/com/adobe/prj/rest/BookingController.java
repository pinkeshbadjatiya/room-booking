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

import com.adobe.prj.entity.Booking;
import com.adobe.prj.service.BookingService;
import com.adobe.prj.service.UserService;
import com.adobe.prj.utils.AuthRoles;

//contains all APIs for booking 
@RestController
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="bookings", method=RequestMethod.GET)
	public @ResponseBody List<Booking> getBookings(HttpServletRequest request) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"admin"}));
		return bookingService.getBookings();
	}
	
	@RequestMapping(value="bookings/{id}", method=RequestMethod.GET)
	public @ResponseBody Booking getBooking(HttpServletRequest request,@PathVariable("id") int id) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"admin"}));
		return bookingService.getBooking(id);
	}
	
	// Along with the payload if you want to send a status code, then use ResponseEntity
	
	@RequestMapping(value="bookings", method=RequestMethod.POST)
	public ResponseEntity<Booking> addBooking(HttpServletRequest request,@RequestBody Booking b) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"admin","user"}));
		bookingService.addBooking(b);
		return new ResponseEntity<>(b, HttpStatus.CREATED);
	}

	@RequestMapping(value="bookings", method=RequestMethod.PUT)
	public ResponseEntity<Booking> updateBooking(HttpServletRequest request,@RequestBody Booking b) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"admin","user"}));
		bookingService.updateBooking(b);
		return new ResponseEntity<>(b, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="bookings", method=RequestMethod.DELETE)
	public ResponseEntity<Booking> deleteBooking(HttpServletRequest request,@RequestBody Booking b) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"admin"}));
		bookingService.deleteBooking(b);
		return new ResponseEntity<>(b, HttpStatus.OK);
	}
	
	@RequestMapping(value="bookings/roomAvailability", method=RequestMethod.GET)
	public @ResponseBody List<Integer> getRoomAvailability(HttpServletRequest request,@RequestBody Booking b) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"admin","user"}));
		return bookingService.getRoomAvailability(b.getRoom().getId(),b.getStartDate(),b.getEndDate());
	}
	
	@RequestMapping(value="bookings/computePrice/{id}", method=RequestMethod.GET)
	public @ResponseBody Booking getPrice(HttpServletRequest request,@PathVariable("id") int id) {
		userService.authenticateUserByAPIKeyAndRole(request, new AuthRoles(new String[]{"admin","user"}));
		return bookingService.getPrice(id);
	}
	
	
}
