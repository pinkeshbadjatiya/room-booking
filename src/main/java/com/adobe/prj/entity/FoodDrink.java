package com.adobe.prj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fooddrinks")
public class FoodDrink {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "price")
	private double price;
	
	@Column(name = "price_type")
	private String price_type;

	public int getId() {
		return id;
	}

	public FoodDrink(double price, String price_type) {
		super();
		this.price = price;
		this.price_type = price_type;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public FoodDrink() {
		super();
	}

	
}
