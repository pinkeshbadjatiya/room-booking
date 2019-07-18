package com.adobe.prj.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adobe.prj.dao.BookingDao;
import com.adobe.prj.entity.Booking;
//service class for booking entity
@Service
public class BookingService {

	@Autowired
	private BookingDao bookingDao;
//	@Autowired
//	private BookedItemDao bookedItemDao;
//	
	public Booking getBooking(int id) {
		//System.out.println(id);
		return bookingDao.getBooking(id);
	}

	public List<Booking> getBookings() {
		return bookingDao.getBookings();
	}
	
	@Transactional
	public void addBooking(Booking b, String role) {
		bookingDao.addBooking(b, role);
	}
	
	@Transactional
	public void deleteBooking(Booking b) {
		bookingDao.deleteBooking(b);
	}

	@Transactional
	public void updateBooking(Booking b, String role) {
		bookingDao.updateBooking(b, role);
	}
	

	public List<Integer> getRoomAvailability(int roomId, Date startDate, Date endDate){
		return bookingDao.getRoomAvailability(roomId, startDate, endDate);
	}
	
//	
//	public Boolean getItemAvailability(BookedItemId booked_item, List<Integer> booked_qty){
//		return bookedItemDao.getItemAvailability(booked_item, booked_qty);
//	}

	public Booking getPrice(int id) {
		return bookingDao.getPrice(id);
	}
}
