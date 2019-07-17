package com.adobe.prj.dao;

import java.util.Date;
import java.util.List;

import com.adobe.prj.entity.Booking;

//interface for booking data access operations
public interface BookingDao {

	List<Booking> getBookings();
	Booking getBooking(int id);
	void addBooking(Booking b, String role);
	void updateBooking(Booking b, String role);
	void deleteBooking(Booking b);
	List<Integer> getRoomAvailability(int roomId, Date startDate, Date endDate);
	Booking getPrice(int id);
}
