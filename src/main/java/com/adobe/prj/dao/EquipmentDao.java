package com.adobe.prj.dao;

import java.util.List;

import com.adobe.prj.entity.Equipment;

public interface EquipmentDao {
	// void addProduct(Product p);
	// List<Product> getProducts();
	// Product getProduct(int id);
	List<Equipment> getEquipments();

	Equipment getEquipment(int id);

	void addEquipment(Equipment e);

	void updateEquipment(Equipment e);

	void deleteEquipment(Equipment e);
	
}
