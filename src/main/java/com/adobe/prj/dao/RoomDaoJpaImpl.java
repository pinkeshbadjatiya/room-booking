package com.adobe.prj.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.adobe.prj.entity.Layout;
import com.adobe.prj.entity.Room;
import com.adobe.prj.exceptions.InvalidParameterOrMissingValue;

@Repository
public class RoomDaoJpaImpl implements RoomDao {

	@PersistenceContext
	private EntityManager em;
	
	
	@Override
    @Transactional
	public void addRoom(Room r) {
		em.persist(r);
	}
	
	
	@Override
	public List<Room> getRooms() {
		String JPQL = "SELECT r FROM Room r";
		TypedQuery<Room> query = em.createQuery(JPQL, Room.class);
		return query.getResultList();
	}

	@Override
	public Room getRoom(int id) {
		Room room = em.find(Room.class, id);
		if (room == null) {
			throw new InvalidParameterOrMissingValue("Invalid Parameter or Missing Value.");
		}
		return room;
	}

	@Override
	@Transactional
	public void updateRoom(Room r) {
		Room _r = em.find(Room.class, r.getId());
		_r.setId(r.getId());
		_r.setBookTypes(r.getBookTypes());
		_r.setCapacity(r.getCapacity());
		_r.setImage(r.getImage());
		_r.setTitle(r.getTitle());
		_r.setStatus(r.getStatus());
		_r.setDescription(r.getDescription());
		_r.setLayoutList(r.getLayoutList());
		_r.setBookTypesPrice(r.getBookTypesPrice());
		_r.setLayoutIds(r.getLayoutIds());
		em.persist(_r);

	}

	@Override
	@Transactional
	public void deleteRoom(Room r) {
		Room _r = em.find(Room.class, r.getId());
		em.remove(_r);
	}

}
