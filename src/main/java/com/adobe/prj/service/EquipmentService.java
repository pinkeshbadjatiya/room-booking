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
