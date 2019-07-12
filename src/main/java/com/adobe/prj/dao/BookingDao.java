package com.adobe.prj.dao;

import java.util.List;

import com.adobe.prj.entity.Booking;

public interface BookingDao {

	List<Booking> getBookings();
	Booking getBooking(int id);
	void addBooking(Booking b);
	void updateBooking(Booking b);
	void deleteBooking(Booking b);
	List<Integer> getAvailability(int roomId);
	public List<Booking> getBookRoom(int roomId);
	Booking getPrice(int id);
}
