package com.adobe.prj.dao;

import java.util.List;

import com.adobe.prj.entity.BookedItem;
import com.adobe.prj.entity.BookedItem.BookedItemId;

public interface BookedItemDao {

	Boolean getItemAvailability(BookedItemId id, List<Integer> duration);

	void updateItemAvailability(BookedItemId id, List<Integer> duration);

	BookedItem getBookedItem(BookedItemId id);

	void addBookedItem(BookedItem bi);

	void updateBookedItem(BookedItem bi);

	void deleteBookedItem(BookedItem bi);

}
