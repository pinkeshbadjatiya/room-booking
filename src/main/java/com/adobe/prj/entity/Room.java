package com.adobe.prj.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="rooms")
public class Room {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="image")
	private String image;

	@Column(name="capacity")
	private int capacity;

	@Column(name="description")
	private String description;

	@Column(name="status")
	private String Status;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name="book_types")
	private List<String> bookTypes = new ArrayList<String>();
	
	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name="book_type_price")
	private List<Double> bookTypesPrice = new ArrayList<Double>();
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="room_layout",
	joinColumns = @JoinColumn(name = "room_id"),
	inverseJoinColumns = @JoinColumn(name = "layout_id"))
	private List<Layout> layoutList = new ArrayList<Layout>();
	
	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name="layout_id")
	private List<Integer> layoutIds = new ArrayList<Integer>();
	
	public int getId() {
		return id;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public List<Layout> getLayoutList() {
		return layoutList;
	}

	public void setLayoutList(List<Layout> layoutList) {
		this.layoutList = layoutList;
	}

	public List<String> getBookTypes() {
		return bookTypes;
	}

	public void setBookTypes(List<String> bookTypes) {
		this.bookTypes = bookTypes;
	}

	public List<Double> getBookTypesPrice() {
		return bookTypesPrice;
	}

	public void setBookTypesPrice(List<Double> bookTypesPrice) {
		this.bookTypesPrice = bookTypesPrice;
	}

	public List<Integer> getLayoutIds() {
		return layoutIds;
	}

	public void setLayoutIds(List<Integer> layoutIds) {
		this.layoutIds = layoutIds;
	}


}
