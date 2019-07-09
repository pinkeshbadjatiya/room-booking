package com.adobe.prj.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adobe.prj.dao.EquipmentDao;
import com.adobe.prj.entity.Equipment;

@Service
public class EquipmentService {

	@Autowired
	private EquipmentDao equipmentDao;
	
	
//	@Transactional
//	public void manageOrder(Order order) {
//
////		userDao.placeOrder(order);
////		List<Item> items = order.getItems();
////		double total = 0.0;
////		for(Item item: items) {
////			Product p = productDao.getProduct(item.getProduct().getId());
////			p.setCount(p.getCount() - item.getQty());
////			total += item.getAmount();
////		}
////		order.setTotal(total);
//		// We made changes to the Product class and still we are not explicitly pushing the changes to the backend,
//		// BECAUSE, the Spring framework maintains dirty checking and ensures syncing of the objects to
//		// to the DB AUTOMAGICALLY!!
//	}
	
	
	public Equipment getEquipment(int id) {
		return equipmentDao.getEquipment(id);
	}

	public List<Equipment> getEquipments() {
		return equipmentDao.getEquipments();
	}
	
	@Transactional
	public void addEquipment(Equipment e) {
		equipmentDao.addEquipment(e);
	}
	
	@Transactional
	public void deleteEquipment(Equipment e) {
		equipmentDao.deleteEquipment(e);
	}

	@Transactional
	public void updateEquipment(Equipment e) {
		equipmentDao.updateEquipment(e);
	}
}
