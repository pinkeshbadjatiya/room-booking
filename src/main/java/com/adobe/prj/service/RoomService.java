package com.adobe.prj.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adobe.prj.dao.LayoutDao;
import com.adobe.prj.dao.RoomDao;
import com.adobe.prj.entity.Layout;
import com.adobe.prj.entity.Room;

@Service
public class RoomService {

	@Autowired
	private RoomDao roomDao;
	
	@Autowired
	private LayoutDao ld;
	
	public Room getRoom(int id) {
		//System.out.println(id);
		return roomDao.getRoom(id);
	}

	public List<Room> getRooms() {
		return roomDao.getRooms();
	}
	
	@Transactional
	public void addRoom(Room r) {
		List<Integer> layoutIds = r.getLayoutIds();
		List<Layout> ans = new ArrayList<Layout>();
		for(int layoutId : layoutIds) {
			ans.add(ld.getLayout(layoutId));
		}
		r.setLayoutList(ans);
		roomDao.addRoom(r);
	}
	
	@Transactional
	public void deleteRoom(Room r) {
		List<Layout> ans = new ArrayList<Layout>();
		r.setLayoutList(ans);
		roomDao.deleteRoom(r);
	}

	@Transactional
	public void updateRoom(Room r) {
		List<Integer> layoutIds = r.getLayoutIds();
		List<Layout> ans = new ArrayList<Layout>();
		for(int layoutId : layoutIds) {
			ans.add(ld.getLayout(layoutId));
		}
		r.setLayoutList(ans);
		roomDao.updateRoom(r);
	}
}
