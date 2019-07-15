package com.adobe.prj.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.adobe.prj.entity.Booking;
import com.adobe.prj.entity.EquipmentOrder;
import com.adobe.prj.entity.FoodDrinkOrder;

//implementation of BookingDao interface
@Repository
public class BookingDaoJpaImpl implements BookingDao {

	@PersistenceContext
	private EntityManager em;

	//adds new booking
	@Override
	@Transactional
	public void addBooking(Booking b) {
		if (b.getEndDate() == null) {
			b.setEndDate(b.getStartDate());
		}
		em.persist(b);
	}

	//returns list of all bookings made by admin and bookings confirmed at user side
	@Override
	public List<Booking> getBookings() {
		String JPQL = "SELECT b FROM Booking b where b.confirmBooking=TRUE";
		TypedQuery<Booking> query = em.createQuery(JPQL, Booking.class);
		return query.getResultList();
	}

	//returns a particular booking based on id
	@Override
	public Booking getBooking(int id) {
		return em.find(Booking.class, id);
	}

	//updates a particular booking after checking the updated fields
	@Override
	@Transactional
	public void updateBooking(Booking b) {
		Booking _b = em.find(Booking.class, b.getId());
		if (b.getId() != 0) {
			_b.setId(b.getId());
		}
//		System.out.println(b.getId());
//		System.out.println(_b.getId());
//		System.out.println(b.getAddress());
		if (b.getAddress() != null) {
		//	System.out.println("setting");
			_b.setAddress(b.getAddress());
		}
		if (b.getAttendees() != 0) {
			_b.setAttendees(b.getAttendees());
		}
		if (b.getStartDate() != null) {
			_b.setStartDate(b.getStartDate());
		}
		if (b.getEndDate() != null) {
			_b.setEndDate(b.getEndDate());
		}
		if (b.getCity() != null) {
			_b.setCity(b.getCity());
		}
		if (b.getCompany() != null) {
			_b.setCompany(b.getCompany());
		}
		if (b.getCountry() != null) {
			_b.setCountry(b.getCountry());
		}
		if (b.getDeposit() != 0) {
			_b.setDeposit(b.getDeposit());
		}
		if (b.getDuration() != null) {
			_b.setDuration(b.getDuration());
		}
		if (b.getEmail() != null) {
			_b.setEmail(b.getEmail());
		}
		if (b.getEquipmentOrders().size() != 0) {
			_b.setEquipmentOrders(b.getEquipmentOrders());
		}
		if (b.getFoodDrinkOrders().size() != 0) {
			_b.setFoodDrinkOrders(b.getFoodDrinkOrders());
		}
		if (b.getLayout() != null) {
			_b.setLayout(b.getLayout());
		}
		if (b.getName() != null) {
			_b.setName(b.getName());
		}
		if (b.getNotes() != null) {
			_b.setNotes(b.getNotes());
		}
		if (b.getPaymentMethod() != null) {
			_b.setPaymentMethod(b.getPaymentMethod());
		}
		if (b.getRoom() != null) {
			_b.setRoom(b.getRoom());
		}
		if (b.getState() != null) {
			_b.setState(b.getState());
		}
		if (b.getStatus() != null) {
			_b.setStatus(b.getStatus());
		}
		if (b.getTitle() != null) {
			_b.setTitle(b.getTitle());
		}
		if (b.getZip() != null) {
			_b.setZip(b.getZip());
		}
		if (b.getHourList().size() != 0) {
			_b.setHourList(b.getHourList());
		}
		if(b.isConfirmBooking()) {
			_b.setConfirmBooking(true);
		}
		em.persist(_b);

	}

	//deletes a particular booking
	@Override
	@Transactional
	public void deleteBooking(Booking b) {
		Booking _b = em.find(Booking.class, b.getId());
		em.remove(_b);
	}

	//returns the availability of a room on a particular date or range of dates
	//returns in the form of hour list
	@Override
	public List<Integer> getRoomAvailability(int roomId, Date startDate, Date endDate) {
		//checks if booking is for single day or multiple days
		if (endDate == null || startDate.equals(endDate)) {

			List<Integer> ans = new ArrayList<>();
			// check for hourly booking in previous multiple day bookings
			if (findBookingsContainingDateForHourlyBooking(roomId, startDate).size() != 0) {
				for (int i = 0; i < 15; i++) {
					ans.add(0);
				}
				return ans;
			} else {
				// check for hourly booking in previous hourly bookings
				return getRoomAvailabilityUtil(roomId, startDate);
			}

		} else {
			// check for multiple day booking in previous multiple day bookings as well as
			// hourly bookings
			List<Integer> ans = new ArrayList<>();
			Calendar start = Calendar.getInstance();
			start.setTime(startDate);
			Calendar end = Calendar.getInstance();
			end.setTime(endDate);

			for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
				if (findBookingsContainingDateForMultipleDaysBooking(roomId, date).size() != 0) {
					for (int i = 0; i < 15; i++) {
						ans.add(0);
					}
				} else {
					for (int i = 0; i < 15; i++) {
						ans.add(1);
					}
				}
			}
			return ans;
		}
	}

	//returns bookings containing a particular start date irrespective of duration of booking
	public List<Booking> findBookingsContainingDateForMultipleDaysBooking(int roomId, Date startDate) {
		String JPQL1 = "SELECT b FROM Booking b JOIN b.room r where r.id=:roomId and :startDate BETWEEN b.startDate and b.endDate";
		Query query = em.createQuery(JPQL1).setParameter("roomId", roomId).setParameter("startDate", startDate);
		return query.getResultList();
	}

	//returns bookings containing a particular start date only for multiple day bookings 
	public List<Booking> findBookingsContainingDateForHourlyBooking(int roomId, Date startDate) {
		String JPQL1 = "SELECT b FROM Booking b JOIN b.room r where r.id=:roomId and b.duration='multiple days' and :startDate BETWEEN b.startDate and b.endDate";
		Query query = em.createQuery(JPQL1).setParameter("roomId", roomId).setParameter("startDate", startDate);
		return query.getResultList();
	}

	//returns list of hours available for a particular room on a particular date
	public List<Integer> getRoomAvailabilityUtil(int roomId, Date bookingDate) {
		List<List<Integer>> hourLists = new ArrayList<List<Integer>>();
		List<Integer> ans = new ArrayList<>();
		List<Booking> bookRows = getBookRoom(roomId, bookingDate);
		for (Booking b : bookRows) {
			int bookId = b.getId();
			String JPQL2 = "SELECT l FROM Booking b JOIN b.hourList l where b.id=:bookId";
			Query query2 = em.createQuery(JPQL2).setParameter("bookId", bookId);
			List<Integer> hrList = query2.getResultList();
			hourLists.add(hrList);
		}
		for (List<Integer> hList : hourLists) {
			for (int i = 0; i < hList.size(); i++) {
				if (hList.get(i) == 1) {
					if (ans.size() < hList.size()) {
						ans.add(i, 1);
					} else {
						ans.set(i, 1);
					}
				} else if (ans.size() < hList.size()) {
					ans.add(i, 0);
				}
			}
		}
		return ans;
	}

	//returns bookings corresponding to a particular room and date
	public List<Booking> getBookRoom(int roomId, Date bookingDate) {
		String JPQL1 = "SELECT b FROM Booking b JOIN b.room r where r.id=:roomId and b.startDate=:bookingDate";
		Query query = em.createQuery(JPQL1).setParameter("roomId", roomId).setParameter("bookingDate", bookingDate);
		return query.getResultList();
	}

	@Override
	@Transactional
	public Booking getPrice(int id) {
		Booking b = em.find(Booking.class, id);

		System.out.println(b);
		double subTotal = 0.00;
		List<EquipmentOrder> equipmentOrderList = b.getEquipmentOrders();
		System.out.println(equipmentOrderList.size());
		for (EquipmentOrder eo : equipmentOrderList) {
			System.out.println(eo);
			eo.setAmount(eo.getEquipment().getPrice() * eo.getQty());
			// em.persist(eo);
			subTotal += eo.getAmount();
		}
		List<FoodDrinkOrder> foodDrinkOrderList = b.getFoodDrinkOrders();
		for (FoodDrinkOrder fo : foodDrinkOrderList) {
			fo.setAmount(fo.getFoodDrink().getPrice() * fo.getQty());
			// em.persist(fo);
			subTotal += fo.getAmount();
		}
		if (b.getDuration().equals("Hour")) {
			List<Integer> hourList = b.getHourList();
			int totalHours = 0;
			for (int hour : hourList) {
				totalHours += hour;
			}
			double roomPrice = 0.00;
			List<String> bookTypes = b.getRoom().getBookTypes();
			for (int i = 0; i < bookTypes.size(); i++) {
				if (bookTypes.get(i).equals("hour")) {
					roomPrice = b.getRoom().getBookTypesPrice().get(i);
				}
			}
			subTotal += (totalHours * roomPrice);
		} else if (b.getDuration().equals("Half-day")) {
			double roomPrice = 0.00;
			List<String> bookTypes = b.getRoom().getBookTypes();
			for (int i = 0; i < bookTypes.size(); i++) {
				if (bookTypes.get(i).equals("Half-day")) {
					roomPrice = b.getRoom().getBookTypesPrice().get(i);
				}
			}
			subTotal += roomPrice;
		} else if (b.getDuration().equals("Multiple days")) {
			double roomPrice = 0.00;
			List<String> bookTypes = b.getRoom().getBookTypes();
			for (int i = 0; i < bookTypes.size(); i++) {
				if (bookTypes.get(i).equals("Multiple days")) {
					roomPrice = b.getRoom().getBookTypesPrice().get(i);
				}
			}
			long difference = b.getEndDate().getTime() - b.getStartDate().getTime();
			int ans = (int) TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
			subTotal += (roomPrice * ans);
		}
		b.setSubTotal(subTotal);
		double tax = 0.1 * subTotal;
		b.setTax(tax);
		double total = subTotal + tax;
		b.setTotal(total);
		em.persist(b);
		return b;
	}

}
