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
