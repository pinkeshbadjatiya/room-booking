package com.adobe.prj.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="tokens")
public class Token {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
		name = "UUID",
		strategy = "com.adobe.prj.utils.APIKeyGenerator"
	)
	
	@Column(name="api_key")
	private String apiKey;
	
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(unique = true)
	private User user;

	@Column(name="authentication_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date authenticationDate = new Date();
	
	@Column(name="role")
	private String role;

	
	
	@Override
	public String toString() {
		return "Token [apiKey=" + apiKey + ", user=" + user + ", authenticationDate=" + authenticationDate + ", role="
				+ role + "]";
	}

	public Token() {
	}

	public Token(User user, String apiKey, Date authenticationDate, String role) {
		this.user = user;
		this.apiKey = apiKey;
		this.authenticationDate = authenticationDate;
		this.role = role;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getAuthenticationDate() {
		return authenticationDate;
	}

	public void setAuthenticationDate(Date authenticationDate) {
		this.authenticationDate = authenticationDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	
}
