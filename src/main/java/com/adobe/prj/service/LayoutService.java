package com.adobe.prj.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adobe.prj.dao.LayoutDao;
import com.adobe.prj.entity.Layout;

@Service
public class LayoutService {

	@Autowired
	private LayoutDao layoutDao;
	
	
	public Layout getLayout(int id) {
		return layoutDao.getLayout(id);
	}

	public List<Layout> getLayouts() {
		return layoutDao.getLayouts();
	}
	
	@Transactional
	public void addLayout(Layout l) {
		layoutDao.addLayout(l);
	}
	
	@Transactional
	public void deleteLayout(Layout l) {
		layoutDao.deleteLayout(l);
	}

	@Transactional
	public void updateLayout(Layout l) {
		layoutDao.updateLayout(l);
	}
}
