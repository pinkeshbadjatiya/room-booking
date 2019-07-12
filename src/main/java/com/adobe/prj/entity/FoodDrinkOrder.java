package com.adobe.prj.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="fooddrink_orders")
public class FoodDrinkOrder {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="fooddrink_id")
	private FoodDrink foodDrink;
	
	private int qty;
	
	private double amount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQty() {
		return qty;
	}

	public FoodDrink getFoodDrink() {
		return foodDrink;
	}

	public void setFoodDrink(FoodDrink foodDrink) {
		this.foodDrink = foodDrink;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
