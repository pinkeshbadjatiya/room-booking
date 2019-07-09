package com.adobe.prj.dao;

import java.util.List;

import com.adobe.prj.entity.Layout;
import com.adobe.prj.entity.User;

public interface LayoutDao {
//	void addProduct(Product p);
//	List<Product> getProducts();
//	Product getProduct(int id);
	List<Layout> getLayouts();
	Layout getLayout(int id);
	void addLayout(Layout l);
	void updateLayout(Layout l);
	void deleteLayout(Layout l);
}
