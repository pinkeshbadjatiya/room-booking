package com.adobe.prj.dao;

import java.util.List;

import com.adobe.prj.entity.Equipment;

// interface for equipment data access operations
public interface EquipmentDao {
	List<Equipment> getEquipments();

	Equipment getEquipment(int id);

	void addEquipment(Equipment e);

	void updateEquipment(Equipment e);

	void deleteEquipment(Equipment e);

}
