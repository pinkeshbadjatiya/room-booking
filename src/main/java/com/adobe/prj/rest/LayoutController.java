package com.adobe.prj.rest;

import java.util.List;

import com.adobe.prj.entity.Layout;
import com.adobe.prj.entity.User;
import com.adobe.prj.service.LayoutService;
import com.adobe.prj.service.UserService;

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
public class LayoutController {
	@Autowired
	private LayoutService layoutService;
	
	@RequestMapping(value="layouts", method=RequestMethod.GET)
	public @ResponseBody List<Layout> getLayouts() {
		return layoutService.getLayouts();
	}
	
	@RequestMapping(value="layouts/{id}", method=RequestMethod.GET)
	public @ResponseBody Layout getLayout(@PathVariable("id") int id) {
		return layoutService.getLayout(id);
	}
	
	// Along with the payload if you want to send a status code, then use ResponseEntity
	
	@RequestMapping(value="layouts", method=RequestMethod.POST)
	public ResponseEntity<Layout> addLayout(@RequestBody Layout l) {
		layoutService.addLayout(l);
		return new ResponseEntity<>(l, HttpStatus.CREATED);
	}

	@RequestMapping(value="layouts", method=RequestMethod.PUT)
	public ResponseEntity<Layout> updateUser(@RequestBody Layout l) {
		layoutService.updateLayout(l);
		return new ResponseEntity<>(l, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="layouts", method=RequestMethod.DELETE)
	public ResponseEntity<Layout> deleteLayout(@RequestBody Layout l) {
		layoutService.deleteLayout(l);
		return new ResponseEntity<>(l, HttpStatus.OK);
	}
	
	
}
