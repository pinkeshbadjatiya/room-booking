package com.adobe.prj.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.adobe.prj.entity.Booking;
import com.adobe.prj.entity.BookingStatus;
import com.adobe.prj.entity.EquipmentOrder;
import com.adobe.prj.entity.FoodDrinkOrder;
import com.adobe.prj.entity.Layout;
import com.adobe.prj.entity.Room;
import com.adobe.prj.exceptions.InvalidParameterOrMissingValue;
import com.adobe.prj.exceptions.NotAuthorized;

//implementation of BookingDao interface
@Repository
public class BookingDaoJpaImpl implements BookingDao {

	@PersistenceContext
	private EntityManager em;

	// adds new booking
	@Override
	@Transactional
	public void addBooking(Booking b, String role) {
		if (b.getEndDate() == null) {
			b.setEndDate(b.getStartDate());
		}
		if (b.getDuration() != null && b.getDuration().equalsIgnoreCase("full day")
				&& b.getEndDate().equals(b.getStartDate())) {
			List<Integer> ans = new ArrayList<>();
			for (int i = 0; i < 15; i++) {
				ans.add(i, 0);
			}
			b.setHourList(ans);
		}
		if (b.getHourList().size() == 0) {
			List<Integer> ans = new ArrayList<>();
			for (int i = 0; i < 15; i++) {
				ans.add(i, 1);
			}
			b.setHourList(ans);
		}

		// Validate booking
		if (b.isConfirmBooking())
			validateBookingWithData(b, role);
		em.persist(b);
	}

	// returns list of all bookings made by admin and bookings confirmed at user
	// side
	@Override
	public List<Booking> getBookings() {
		String JPQL = "SELECT b FROM Booking b where b.confirmBooking=TRUE";
		TypedQuery<Booking> query = em.createQuery(JPQL, Booking.class);
		if (query == null) {
			throw new InvalidParameterOrMissingValue("No bookings found.");
		}
		return query.getResultList();

	}

	// returns a particular booking based on id
	@Override
	public Booking getBooking(int id) {
		Booking ans = em.find(Booking.class, id);

		if (ans == null) {
			throw new InvalidParameterOrMissingValue("No booking with the given id found.");
		}
		return em.find(Booking.class, id);
	}

	// updates a particular booking after checking the updated fields
	@Override
	@Transactional
	public void updateBooking(Booking b, String role) {
		Booking _b = em.find(Booking.class, b.getId());
		
		
		// Process data for consistency
		if (b.getEndDate() == null) {
			b.setEndDate(b.getStartDate());
		}
		if(b.getDuration()!=null && b.getDuration().equalsIgnoreCase("full day") && b.getEndDate().equals(b.getStartDate())) {
			List<Integer> ans = new ArrayList<>();
			for(int i=0;i<15;i++) {
				ans.add(i,0);
			}
			b.setHourList(ans);
		}
		if(b.getHourList().size()==0) {
			List<Integer> ans = new ArrayList<>();
			for(int i=0;i<15;i++) {
				ans.add(i,1);
			}
			b.setHourList(ans);
		}
		
		
		
		if (b.getId() != 0) {
			_b.setId(b.getId());
		}

		if (b.getAddress() != null) {
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
			if (role == "user")
				throw new NotAuthorized("Not authorized to set Status");
			_b.setStatus(b.getStatus());
		} else if (role == "user") {
			_b.setStatus(BookingStatus.PENDING);
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

		if (b.isConfirmBooking()) {
			validateBookingWithId(_b.getId(), role);
			_b.setConfirmBooking(true);
		}

		em.persist(_b);

	}

	private void validateBookingWithId(int bookingId, String role) {
		Booking bk = em.find(Booking.class, bookingId);
		validateBookingWithData(bk, role);
	}

	private void validateBookingWithData(Booking bk, String role) {
		// Check Room
		Room room = bk.getRoom();
		if (room == null)
			throw new InvalidParameterOrMissingValue("No room specified");
		room = em.find(Room.class, room.getId());

		// Check layout
		Layout layout = bk.getLayout();
		if (layout == null)
			throw new InvalidParameterOrMissingValue("No layout specified");

		// Check Layout in Room
		System.out.println(layout.getId());
		if (!room.getLayoutIds().contains(layout.getId()))
			throw new InvalidParameterOrMissingValue("Invalid Layout for a Room");

		// Check sDate & eDate
		Date sDate = bk.getStartDate();
		Date eDate = bk.getEndDate();
		if (sDate == null)
			throw new InvalidParameterOrMissingValue("Invalid Start date");

		// Check room availability
		List<Integer> bookedHourList = bk.getHourList();
		System.out.println(bookedHourList);
		List<Integer> availableHourList = getRoomAvailability(room.getId(), sDate, eDate);
		System.out.println(availableHourList);
		for (int i = 0; i < availableHourList.size(); i++) {
			if (bookedHourList.get(i) == 0 && availableHourList.get(i) == 0)
				throw new InvalidParameterOrMissingValue("Already Booked");
		}

		// Check Duration
		HashSet<String> hashSet = new HashSet<String>();
		hashSet.add("full day");
		hashSet.add("half day");
		hashSet.add("multiple days");
		hashSet.add("hour");
		if (!hashSet.contains(bk.getDuration().toLowerCase()))
			throw new InvalidParameterOrMissingValue("Invalid Duration");

	}

	// deletes a particular booking
	@Override
	@Transactional
	public void deleteBooking(Booking b) {
		Booking _b = em.find(Booking.class, b.getId());
		if (_b == null) {
			throw new InvalidParameterOrMissingValue("No such booking found to be deleted.");
		} else
			em.remove(_b);
	}

	// returns the availability of a room on a particular date or range of dates
	// returns in the form of hour list
	@Override
	public List<Integer> getRoomAvailability(int roomId, Date startDate, Date endDate) {
		System.out.println(startDate);
		// checks if booking is for single day or multiple days
		if (endDate == null || startDate.equals(endDate)) {

			List<Integer> availability = new ArrayList<>();
			// check for hourly booking in previous multiple day bookings
			if (findBookingsContainingDateForHourlyBooking(roomId, startDate).size() != 0) {
				for (int i = 0; i < 15; i++) {
					availability.add(0);
				}
				return availability;
			} else {
				// check for hourly booking in previous hourly bookings
				return getRoomAvailabilityUtil(roomId, startDate);
			}

		} else {
			// check for multiple day booking in previous multiple day bookings as well as
			// hourly bookings
			List<Integer> availability = new ArrayList<>();
			Calendar start = Calendar.getInstance();
			start.setTime(startDate);
			Calendar end = Calendar.getInstance();
			end.setTime(endDate);

			for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
				if (findBookingsContainingDateForMultipleDaysBooking(roomId, date).size() != 0) {
					for (int i = 0; i < 15; i++) {
						availability.add(0);
					}
				} else {
					for (int i = 0; i < 15; i++) {
						availability.add(1);
					}
				}
			}
			return availability;
		}
	}

	// returns bookings containing a particular start date irrespective of duration
	// of booking
	private List<Booking> findBookingsContainingDateForMultipleDaysBooking(int roomId, Date startDate) {
		String JPQL1 = "SELECT b FROM Booking b JOIN b.room r where r.id=:roomId and b.confirmBooking=TRUE and :startDate BETWEEN b.startDate and b.endDate";
		Query query = em.createQuery(JPQL1).setParameter("roomId", roomId).setParameter("startDate", startDate);
		return query.getResultList();
	}

	// returns bookings containing a particular start date only for multiple day
	// bookings
	private List<Booking> findBookingsContainingDateForHourlyBooking(int roomId, Date startDate) {
		String JPQL1 = "SELECT b FROM Booking b JOIN b.room r where r.id=:roomId and b.confirmBooking=TRUE and b.duration='multiple days' and :startDate BETWEEN b.startDate and b.endDate";
		Query query = em.createQuery(JPQL1).setParameter("roomId", roomId).setParameter("startDate", startDate);
		return query.getResultList();
	}

	// returns list of hours available for a particular room on a particular date
	private List<Integer> getRoomAvailabilityUtil(int roomId, Date bookingDate) {
		List<List<Integer>> hourLists = new ArrayList<List<Integer>>();
		List<Integer> availability = new ArrayList<>();
		for (int i = 0; i < 15; i++) {
			availability.add(i, 1);
		}
		List<Booking> bookingRows = getBookRoom(roomId, bookingDate);
		for (Booking b : bookingRows) {
			int bookId = b.getId();
			String JPQL2 = "SELECT l FROM Booking b JOIN b.hourList l where b.id=:bookId";
			Query query2 = em.createQuery(JPQL2).setParameter("bookId", bookId);
			List<Integer> hourList = query2.getResultList();
			hourLists.add(hourList);
		}
		for (List<Integer> hourList : hourLists) {
			for (int i = 0; i < hourList.size(); i++) {
				if (hourList.get(i) == 0) {
					availability.set(i, 0);
				}
			}
		}
		return availability;
	}

	// returns bookings corresponding to a particular room and date
	public List<Booking> getBookRoom(int roomId, Date bookingDate) {
		String JPQL1 = "SELECT b FROM Booking b JOIN b.room r where r.id=:roomId and b.confirmBooking=TRUE and b.startDate=:bookingDate";
		Query query = em.createQuery(JPQL1).setParameter("roomId", roomId).setParameter("bookingDate", bookingDate);
		return query.getResultList();
	}

	// returns final booking object along with calculated price
	@Override
	@Transactional
	public Booking getPrice(int id) {
		Booking b = em.find(Booking.class, id);

		System.out.println(b);
		double subTotal = 0.00;
		// add total amount of equipments
		List<EquipmentOrder> equipmentOrderList = b.getEquipmentOrders();
		System.out.println(equipmentOrderList.size());
		for (EquipmentOrder equipmentOrder : equipmentOrderList) {
			System.out.println(equipmentOrder);
			equipmentOrder.setAmount(equipmentOrder.getEquipment().getPrice() * equipmentOrder.getQty());
			subTotal += equipmentOrder.getAmount();
		}
		// add total amount of food and drinks
		List<FoodDrinkOrder> foodDrinkOrderList = b.getFoodDrinkOrders();
		for (FoodDrinkOrder foodDrinkOrder : foodDrinkOrderList) {
			foodDrinkOrder.setAmount(foodDrinkOrder.getFoodDrink().getPrice() * foodDrinkOrder.getQty());
			subTotal += foodDrinkOrder.getAmount();
		}
		// calculate price for hourly booking
		if (b.getDuration().equalsIgnoreCase("Hour")) {
			List<Integer> hourList = b.getHourList();
			int totalHours = 0;
			for (int hour : hourList) {
				totalHours += hour;
			}
			double roomPrice = 0.00;
			List<String> bookTypes = b.getRoom().getBookTypes();
			for (int i = 0; i < bookTypes.size(); i++) {
				if (bookTypes.get(i).equalsIgnoreCase("hour")) {
					roomPrice = b.getRoom().getBookTypesPrice().get(i);
				}
			}
			subTotal += (totalHours * roomPrice);
		} else if (b.getDuration().equalsIgnoreCase("Half day")) {
			// calculate price for half day booking
			double roomPrice = 0.00;
			List<String> bookTypes = b.getRoom().getBookTypes();
			for (int i = 0; i < bookTypes.size(); i++) {
				if (bookTypes.get(i).equalsIgnoreCase("Half day")) {
					roomPrice = b.getRoom().getBookTypesPrice().get(i);
				}
			}
			subTotal += roomPrice;
		} else if (b.getDuration().equalsIgnoreCase("Multiple days")) {
			// calculate price for multiple day booking
			double roomPrice = 0.00;
			List<String> bookTypes = b.getRoom().getBookTypes();
			for (int i = 0; i < bookTypes.size(); i++) {
				if (bookTypes.get(i).equalsIgnoreCase("Multiple days")) {
					roomPrice = b.getRoom().getBookTypesPrice().get(i);
				}
			}
			long difference = b.getEndDate().getTime() - b.getStartDate().getTime();
			int numDays = (int) TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
			subTotal += (roomPrice * numDays);
		} else if (b.getDuration().equalsIgnoreCase("Full day")) {
			// calculate price for full day booking
			double roomPrice = 0.00;
			List<String> bookTypes = b.getRoom().getBookTypes();
			for (int i = 0; i < bookTypes.size(); i++) {
				if (bookTypes.get(i).equalsIgnoreCase("Full day")) {
					roomPrice = b.getRoom().getBookTypesPrice().get(i);
				}
			}
			subTotal += roomPrice;
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
