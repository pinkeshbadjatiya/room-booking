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
	
	@Column(name = "editIt")
	private Boolean editIt;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Boolean getEditIt() {
		return editIt;
	}

	public void setEditIt(Boolean editIt) {
		this.editIt = editIt;
	}

	public int getId() {
		return id;
	}

	public FoodDrink(int id, String title, double price, Boolean editIt) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.editIt = editIt;
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
