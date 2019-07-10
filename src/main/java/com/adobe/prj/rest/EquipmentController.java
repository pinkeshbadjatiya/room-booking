package com.adobe.prj.rest;

import java.util.List;

import com.adobe.prj.entity.Equipment;
import com.adobe.prj.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EquipmentController {
	@Autowired
	private EquipmentService equipmentService;
	
	@RequestMapping(value="equipments", method=RequestMethod.GET)
	public @ResponseBody List<Equipment> getEquipments() {
		return equipmentService.getEquipments();
	}
	
	@RequestMapping(value="equipments/{id}", method=RequestMethod.GET)
	public @ResponseBody Equipment getEquipment(@PathVariable("id") int id) {
		return equipmentService.getEquipment(id);
	}
	
	// Along with the payload if you want to send a status code, then use ResponseEntity
	
	@RequestMapping(value="equipments", method=RequestMethod.POST)
	public ResponseEntity<Equipment> addEquipment(@RequestBody Equipment e) {
		equipmentService.addEquipment(e);
		return new ResponseEntity<>(e, HttpStatus.CREATED);
	}

	@RequestMapping(value="equipments", method=RequestMethod.PUT)
	public ResponseEntity<Equipment> updateEquipment(@RequestBody Equipment e) {
		equipmentService.updateEquipment(e);
		return new ResponseEntity<>(e, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="equipments", method=RequestMethod.DELETE)
	public ResponseEntity<Equipment> deleteEquipment(@RequestBody Equipment e) {
		equipmentService.deleteEquipment(e);
		return new ResponseEntity<>(e, HttpStatus.OK);
	}
}
