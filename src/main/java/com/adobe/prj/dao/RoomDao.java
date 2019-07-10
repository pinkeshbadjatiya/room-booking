package com.adobe.prj.dao;

import java.util.List;

import com.adobe.prj.entity.Room;

public interface RoomDao {

	List<Room> getRooms();
	Room getRoom(int id);
	void addRoom(Room r);
	void updateRoom(Room r);
	void deleteRoom(Room r);
}
