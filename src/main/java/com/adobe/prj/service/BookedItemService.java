package com.adobe.prj.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adobe.prj.dao.BookedItemDao;
import com.adobe.prj.entity.BookedItem;
import com.adobe.prj.entity.BookedItem.BookedItemId;

@Service
public class BookedItemService {

	@Autowired
	private BookedItemDao bookedItemDao;

	// @Transactional
	// public void manageOrder(Order order) {
	//
	//// userDao.placeOrder(order);
	//// List<Item> items = order.getItems();
	//// double total = 0.0;
	//// for(Item item: items) {
	//// Product p = productDao.getProduct(item.getProduct().getId());
	//// p.setCount(p.getCount() - item.getQty());
	//// total += item.getAmount();
	//// }
	//// order.setTotal(total);
	// // We made changes to the Product class and still we are not explicitly
	// pushing the changes to the backend,
	// // BECAUSE, the Spring framework maintains dirty checking and ensures
	// syncing of the objects to
	// // to the DB AUTOMATICALLY!!
	// }

	public BookedItem getBookedItem(BookedItemId bi) {
		return bookedItemDao.getBookedItem(bi);
	}

	@Transactional
	public void addBookedItem(BookedItem bi) {
		bookedItemDao.addBookedItem(bi);
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
	public Boolean getItemAvailability(BookedItemId id, List<Integer> duration) {
		return bookedItemDao.getItemAvailability(id, duration);
	}

	@Transactional
	public void updateItemAvailability(BookedItemId id, List<Integer> duration) {
		bookedItemDao.updateItemAvailability(id, duration);
	}
}
