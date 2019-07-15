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

import com.adobe.prj.entity.Booking;
import com.adobe.prj.entity.Room;
import com.adobe.prj.service.BookingService;

@RestController
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@RequestMapping(value="bookings", method=RequestMethod.GET)
	public @ResponseBody List<Booking> getBookings() {
		return bookingService.getBookings();
	}
	
	@RequestMapping(value="bookings/{id}", method=RequestMethod.GET)
	public @ResponseBody Booking getBooking(@PathVariable("id") int id) {
		return bookingService.getBooking(id);
	}
	
	// Along with the payload if you want to send a status code, then use ResponseEntity
	
	@RequestMapping(value="bookings", method=RequestMethod.POST)
	public ResponseEntity<Booking> addBooking(@RequestBody Booking b) {
		bookingService.addBooking(b);
		return new ResponseEntity<>(b, HttpStatus.CREATED);
	}

	@RequestMapping(value="bookings", method=RequestMethod.PUT)
	public ResponseEntity<Booking> updateBooking(@RequestBody Booking b) {
		bookingService.updateBooking(b);
		return new ResponseEntity<>(b, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="bookings", method=RequestMethod.DELETE)
	public ResponseEntity<Booking> deleteBooking(@RequestBody Booking b) {
		bookingService.deleteBooking(b);
		return new ResponseEntity<>(b, HttpStatus.OK);
	}
	
	@RequestMapping(value="bookings/roomAvailability", method=RequestMethod.GET)
	public @ResponseBody List<Integer> getAvailability(@RequestBody Booking b) {
		return bookingService.getAvailability(b.getRoom().getId(),b.getStartDate(),b.getEndDate());
	}
	
	@RequestMapping(value="bookings/room/specific", method=RequestMethod.GET)
	public @ResponseBody List<Booking> getBookRoom(@RequestBody Booking b) {
		return bookingService.getBookRoom(b.getRoom().getId(),b.getStartDate());
	}
	
	@RequestMapping(value="bookings/computePrice/{id}", method=RequestMethod.GET)
	public @ResponseBody Booking getPrice(@PathVariable("id") int id) {
		return bookingService.getPrice(id);
	}
	
	
}
