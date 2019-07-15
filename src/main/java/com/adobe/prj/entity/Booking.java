package com.adobe.prj.entity;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

//class for booking entity
@Entity
@Table(name="bookings")
public class Booking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	@Column(name="end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	
	@Column(name="attendees")
	private int attendees;
	
	@ManyToOne
	@JoinColumn(name="room_id")
	private Room room;
	
	@ManyToOne
	@JoinColumn(name="layout_id")
	private Layout layout;
	
	@Column(name="duration")
	private String duration;
	
	//stores a boolean to indicate if booking is confirmed by user
	@Column(name="confirm_booking")
	private boolean confirmBooking;
	
	@Column(name="status")
	private Status status;
	
	//store list of hours indicating booked slots for meeting
	@ElementCollection(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@Column(name="hour_list")
	private List<Integer> hourList = new ArrayList<Integer>();


	@Column(name="payment_method")
	private String paymentMethod;
	
	@Column(name="sub_total")
	private double subTotal;
	
	@Column(name="tax")
	private double tax;
	
	@Column(name="total")
	private double total;
	
	@Column(name="deposit")
	private double deposit;
	
	//list of equipments booked for the meeting
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="booking_id")
	private List<EquipmentOrder> equipmentOrders = new ArrayList<EquipmentOrder>();
	
	//list of food and drinks booked for the meeting
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="booking_id")
	private List<FoodDrinkOrder> foodDrinkOrders = new ArrayList<FoodDrinkOrder>();
	
	@Column(name="title")
	private String title;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="notes")
	private String notes;
	
	@Column(name="company")
	private String company;
	
	@Column(name="address")
	private String address;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="zip")
	private String zip;
	
	@Column(name="country")
	private String country;

	
	public List<Integer> getHourList() {
		return hourList;
	}

	
	@Override
	public String toString() {
		return "Booking [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", attendees=" + attendees
				+ ", room=" + room + ", layout=" + layout + ", duration=" + duration + ", confirmBooking="
				+ confirmBooking + ", status=" + status + ", hourList=" + hourList + ", paymentMethod=" + paymentMethod
				+ ", subTotal=" + subTotal + ", tax=" + tax + ", total=" + total + ", deposit=" + deposit
				+ ", equipmentOrders=" + equipmentOrders + ", foodDrinkOrders=" + foodDrinkOrders + ", title=" + title
				+ ", name=" + name + ", email=" + email + ", notes=" + notes + ", company=" + company + ", address="
				+ address + ", city=" + city + ", state=" + state + ", zip=" + zip + ", country=" + country + "]";
	}


	public void setHourList(List<Integer> hourList) {
//		if(hourList.size()<15) {
//			for(int i=hourList.size()-1;i<15;i++) {
//				hourList.add(i,0);
//			}
//		}
		this.hourList = hourList;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isConfirmBooking() {
		return confirmBooking;
	}

	public void setConfirmBooking(boolean confirmBooking) {
		this.confirmBooking = confirmBooking;
	}

	public int getAttendees() {
		return attendees;
	}

	public void setAttendees(int attendees) {
		this.attendees = attendees;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getDeposit() {
		return deposit;
	}

	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}

	public List<EquipmentOrder> getEquipmentOrders() {
		return equipmentOrders;
	}

	public void setEquipmentOrders(List<EquipmentOrder> equipmentOrders) {
		this.equipmentOrders = equipmentOrders;
	}

	public List<FoodDrinkOrder> getFoodDrinkOrders() {
		return foodDrinkOrders;
	}

	public void setFoodDrinkOrders(List<FoodDrinkOrder> foodDrinkOrders) {
		this.foodDrinkOrders = foodDrinkOrders;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
