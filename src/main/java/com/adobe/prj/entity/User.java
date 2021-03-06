package com.adobe.prj.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="users")
public class User {
	@Id
	private String email;
	
	@Column(name="name")
	private String name;
	
	
	@Column(name="password")
	private String password;

	@Column(name="role")
	private String role;

	@Column(name="phone")
	private String phone;

	@Column(name="status")
	private String status;

	@Override
	public String toString() {
		return "User [email=" + email + ", name=" + name + ", password=" + password + ", role=" + role + ", phone="
				+ phone + ", status=" + status + ", registrationDate=" + registrationDate + "]";
	}

	@Column(name="registration_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date registrationDate = new Date();
	
	
	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
