package com.adobe.prj.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adobe.prj.dao.BookedItemDao;
import com.adobe.prj.entity.BookedItem;
import com.adobe.prj.entity.BookedItem.BookedItemId;
import com.adobe.prj.entity.EquipmentOrder;

@Service
public class BookedItemService {

	@Autowired
	private BookedItemDao bookedItemDao;

	public BookedItem getBookedItem(BookedItemId bi) {
		return bookedItemDao.getBookedItem(bi);
	}

	@Transactional
	public void addBookedItem(EquipmentOrder eq_order, String equipment_type) {

		// TODO: Get item availability
		
//		start_date = eq_order.get
//		// Fetch BookedItem if exists
//		BookedItemId biId = new BookedItemId();
//		biId.setItem_id(eq_order.getId());
//		biId.setItem_type(equipment_type);
//		biId.setDate(date);
//		
//		
//		BookedItem bi = new BookedItem();
//		int qty = eq_order.getQty();
//		List<Integer> hour_list = new ArrayList<Integer>();
//		hour_list.add(0);
//		bi.setBooked_qty();
//		bi.
		
//		bookedItemDao.addBookedItem(bi);
	}

	@Transactional
	public void deleteBookedItem(BookedItem bi) {
		bookedItemDao.deleteBookedItem(bi);
	}

	@Transactional
	public void updateBookedItem(BookedItem bi) {
		bookedItemDao.updateBookedItem(bi);
	}

	@Transactional
	public Boolean getItemAvailability(BookedItemId bid, List<Integer> booked_duration) {
		return bookedItemDao.getItemAvailability(bid, booked_duration);
	}

	@Transactional
	public void updateItemAvailability(BookedItemId id, List<Integer> duration) {
		bookedItemDao.updateItemAvailability(id, duration);
	}
}
