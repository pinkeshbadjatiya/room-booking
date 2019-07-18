package com.adobe.prj.dao;

import java.util.List;

import com.adobe.prj.entity.Layout;

public interface LayoutDao {
	List<Layout> getLayouts();
	Layout getLayout(int id);
	void addLayout(Layout l);
	void updateLayout(Layout l);
	void deleteLayout(Layout l);
}
