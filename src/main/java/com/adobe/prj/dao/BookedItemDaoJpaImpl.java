package com.adobe.prj.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.adobe.prj.entity.BookedItem;
import com.adobe.prj.entity.BookedItem.BookedItemId;
import com.adobe.prj.exceptions.InvalidId;
import com.adobe.prj.exceptions.InvalidParameterOrMissingValue;

@Repository // All 'Dao' classes should have repository
public class BookedItemDaoJpaImpl implements BookedItemDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public BookedItem getBookedItem(BookedItemId id) {
		// Limitation of find is that it can only find based on the primary key
		// 'find' basically looks at the annotations @Table and @Id to generate
		// the SQL
		BookedItem ans = em.find(BookedItem.class, id);
		if (ans == null) {
			throw new InvalidParameterOrMissingValue("No item with the given id found.");
		}
		return ans;
	}

	@Override
	@Transactional
	public void updateBookedItem(BookedItem bi) {
		BookedItem _bi = em.find(BookedItem.class, bi.getId());

		if (_bi == null) {
			throw new InvalidParameterOrMissingValue("No item with the given id found. So, updation not possible.");
		} else
			_bi.setId(_bi.getId());
		em.persist(_bi);
	}

	@Override
	@Transactional
	public void deleteBookedItem(BookedItem bi) {
		BookedItem _bi = em.find(BookedItem.class, bi.getId());
		if (_bi == null) {
			throw new InvalidParameterOrMissingValue("No such item found to be deleted.");
		} else
			em.remove(_bi);
	}

	@Override
	@Transactional // Ensures the whole function gets executed in an atomic
					// fashion. If not, then it rollsback the whole operation.
	public void addBookedItem(BookedItem bi) {
		em.persist(bi);
	}

	@Override
	@Transactional
	public Boolean getItemAvailability(BookedItemId booked_item, List<Integer> booked_qty) {

		String JPQL = "SELECT bi FROM BookedItem bi where bi.id=:booked_item";
		TypedQuery<BookedItem> query = em.createQuery(JPQL, BookedItem.class);
		query.setParameter("booked_item", booked_item);
		BookedItem booked_item1 = query.getResultList().get(0);
		if (booked_item1 == null) {
			throw new InvalidId("No item found. Please check the Id given for the item.");
		}

		// Query query = em.createQuery(JPQL1).setParameter("roomId", roomId);
		// List<Integer> entry = query.getResultList();

		else {
			int countsofar = 0;
			for (int i = booked_qty.get(0); i <= booked_qty.get(1); i++) {
				countsofar += booked_item1.getBooked_qty().get(i);
			}

			if (countsofar > 10)
				return false;

			return true;
		}
	}

	// public List<List<Integer>> getAvailability(int roomId) {
	// List<List<Integer>> ans = new ArrayList<List<Integer>>();
	//
	// List<Booking> bookRows = getBookRoom(roomId);
	// for (Booking b : bookRows) {
	// int bookId = b.getId();
	// String JPQL2 = "SELECT l FROM Booking b JOIN b.hourList l where
	// b.id=:bookId";
	// Query query2 = em.createQuery(JPQL2).setParameter("bookId", bookId);
	// List<Integer> hrList = query2.getResultList();
	// ans.add(hrList);
	// }
	//
	// return ans;
	// }

	@Override
	@Transactional
	public void updateItemAvailability(BookedItemId booked_item_id, List<Integer> duration) {
		BookedItem booked_item = em.find(BookedItem.class, booked_item_id);

		List<Integer> quantities = booked_item.getBooked_qty();
		if (quantities.size() == 0) {
			throw new InvalidParameterOrMissingValue("No item with the given id found. So, updation not possible.");
		} 
			for (int i = duration.get(0); i <= duration.get(1); i++) {
				quantities.set(i, quantities.get(i) + 1);
			}
			booked_item.setBooked_qty(quantities);
			em.persist(booked_item);
		
	}

}
