package com.adobe.prj.dao;

import java.util.ArrayList;
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

@Repository
public class BookingDaoJpaImpl implements BookingDao {

	@PersistenceContext
	private EntityManager em;
	
	
	@Override
    @Transactional
	public void addBooking(Booking b) {
			em.persist(b);
	}
	
	
	@Override
	public List<Booking> getBookings() {
		String JPQL = "SELECT b FROM Booking b";
		TypedQuery<Booking> query = em.createQuery(JPQL, Booking.class);
		return query.getResultList();
	}

	@Override
	public Booking getBooking(int id) {
		return em.find(Booking.class, id);
	}

	@Override
	@Transactional
	public void updateBooking(Booking b) {
		Booking _b = em.find(Booking.class, b.getId());
			if(b.getId()!=0) {
				_b.setId(b.getId());
			}
			if(!b.getAddress().equals(null)) {
				_b.setAddress(b.getAddress());
			}
			if(b.getAttendees()!=0) {
				_b.setAttendees(b.getAttendees());
			}
			if(!b.getStartDate().equals(null)) {
				_b.setStartDate(b.getStartDate());
			}
			if(!b.getEndDate().equals(null)) {
				_b.setEndDate(b.getEndDate());
			}
			if(!b.getCity().equals(null)) {
				_b.setCity(b.getCity());
			}
			if(!b.getCompany().equals(null)) {
				_b.setCompany(b.getCompany());
			}
			if(!b.getCountry().equals(null)) {
				_b.setCountry(b.getCountry());
			}
			if(b.getDeposit()!=0) {
				_b.setDeposit(b.getDeposit());
			}
			if(!b.getDuration().equals(null)) {
				_b.setDuration(b.getDuration());
			}
			if(!b.getEmail().equals(null)) {
				_b.setEmail(b.getEmail());
			}
			if(b.getEquipmentOrders().size()!=0) {
				_b.setEquipmentOrders(b.getEquipmentOrders());
			}
			if(b.getFoodDrinkOrders().size()!=0) {
				_b.setFoodDrinkOrders(b.getFoodDrinkOrders());
			}
			if(!b.getLayout().equals(null)) {
				_b.setLayout(b.getLayout());
			}
			if(!b.getName().equals(null)) {
				_b.setName(b.getName());
			}
			if(!b.getNotes().equals(null)) {
				_b.setNotes(b.getNotes());
			}
			if(!b.getPaymentMethod().equals(null)) {
				_b.setPaymentMethod(b.getPaymentMethod());
			}
			if(!b.getRoom().equals(null)) {
				_b.setRoom(b.getRoom());
			}
			if(!b.getState().equals(null)) {
				_b.setState(b.getState());
			}
			if(!b.getStatus().equals(null)) {
				_b.setStatus(b.getStatus());
			}
//			_b.setSubTotal(b.getSubTotal());
//			_b.setTax(b.getTax());
			if(!b.getTitle().equals(null)) {
				_b.setTitle(b.getTitle());
			}
//			_b.setTotal(b.getTotal());
			if(!b.getZip().equals(null)) {
				_b.setZip(b.getZip());
			}
			if(b.getHourList().size()!=0) {
				_b.setHourList(b.getHourList());
			}
			em.persist(_b);

	}

	@Override
	@Transactional
	public void deleteBooking(Booking b) {
		Booking _b = em.find(Booking.class, b.getId());
		em.remove(_b);
	}

	@Override
	public List<Integer> getAvailability(int roomId,Date startDate,Date endDate) {
		if(multipleDayAvailability(roomId, startDate).size()!=0) {
			List<Integer> ans = new ArrayList<>();
			for(int i=0;i<15;i++) {
				ans.add(1);
			}
			return ans;
		}
		if(endDate==null||startDate.equals(endDate)) {
			return getAvailabilityUtil(roomId, startDate);
		}else {
			List<Integer> ans = new ArrayList<>();
			if(multipleDayAvailability(roomId, endDate).size()!=0) {
				for(int i=0;i<15;i++) {
					ans.add(1);
				}
			}else {
				for(int i=0;i<15;i++) {
					ans.add(0);
				}
			}
			return ans;
		}
	}
	
	public List<Booking> multipleDayAvailability(int roomId,Date startDate){
		String JPQL1 = "SELECT b FROM Booking b JOIN b.room r where r.id=:roomId and b.duration='multiple days' and :startDate BETWEEN b.startDate and b.endDate";
		Query query = em.createQuery(JPQL1).setParameter("roomId", roomId).setParameter("startDate", startDate);
		return query.getResultList();
	}
	
	public List<Integer> getAvailabilityUtil(int roomId,Date bookingDate){
		List<List<Integer>> hourLists = new ArrayList<List<Integer>>();
		List<Integer> ans = new ArrayList<>();
	List<Booking> bookRows = getBookRoom(roomId,bookingDate);
	for(Booking b:bookRows) {
		int bookId = b.getId();
		String JPQL2 = "SELECT l FROM Booking b JOIN b.hourList l where b.id=:bookId";
		Query query2 = em.createQuery(JPQL2).setParameter("bookId", bookId);
		List<Integer> hrList = query2.getResultList();
			hourLists.add(hrList);
	   }
		for(List<Integer> hList:hourLists) {
			for(int i=0;i<hList.size();i++) {
				if(hList.get(i)==1) {
					if(ans.size()<hList.size()) {
						ans.add(i,1);
					}else {
						ans.set(i, 1);
					}
				}else if(ans.size()<hList.size()){
					ans.add(i, 0);
				}
			}
	    }
		return ans;
	}
	
	@Override
	public List<Booking> getBookRoom(int roomId,Date bookingDate){
		String JPQL1 = "SELECT b FROM Booking b JOIN b.room r where r.id=:roomId and b.startDate=:bookingDate";
		Query query = em.createQuery(JPQL1).setParameter("roomId", roomId).setParameter("bookingDate", bookingDate);
		return query.getResultList();
	}


	@Override
	@Transactional
	public Booking getPrice(int id) {
		Booking b = em.find(Booking.class, id);

		System.out.println(b);
		double subTotal=0.00;
		List<EquipmentOrder> equipmentOrderList = b.getEquipmentOrders();
		System.out.println(equipmentOrderList.size());
		for(EquipmentOrder eo : equipmentOrderList) {
			System.out.println(eo);
			eo.setAmount(eo.getEquipment().getPrice()*eo.getQty());
//			em.persist(eo);
			subTotal += eo.getAmount();
		}
		List<FoodDrinkOrder> foodDrinkOrderList = b.getFoodDrinkOrders();
		for(FoodDrinkOrder fo : foodDrinkOrderList) {
			fo.setAmount(fo.getFoodDrink().getPrice()*fo.getQty());
//			em.persist(fo);
			subTotal += fo.getAmount();
		}
		if(b.getDuration().equals("Hour")) {
			List<Integer> hourList = b.getHourList();
			int totalHours=0;
			for(int hour:hourList) {
				totalHours += hour;
			}
			double roomPrice=0.00;
			List<String> bookTypes = b.getRoom().getBookTypes();
			for(int i=0;i<bookTypes.size();i++) {
				if(bookTypes.get(i).equals("hour")) {
					roomPrice = b.getRoom().getBookTypesPrice().get(i);
				}
			}
			subTotal += (totalHours*roomPrice);
		}else if(b.getDuration().equals("Half-day")) {
			double roomPrice=0.00;
			List<String> bookTypes = b.getRoom().getBookTypes();
			for(int i=0;i<bookTypes.size();i++) {
				if(bookTypes.get(i).equals("Half-day")) {
					roomPrice = b.getRoom().getBookTypesPrice().get(i);
				}
			}
			subTotal += roomPrice;
		}else if(b.getDuration().equals("Multiple days")) {
			double roomPrice=0.00;
			List<String> bookTypes = b.getRoom().getBookTypes();
			for(int i=0;i<bookTypes.size();i++) {
				if(bookTypes.get(i).equals("Multiple days")) {
					roomPrice = b.getRoom().getBookTypesPrice().get(i);
				}
			}
			long difference = b.getEndDate().getTime()-b.getStartDate().getTime();
			int ans = (int)TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
			subTotal += (roomPrice*ans);
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
