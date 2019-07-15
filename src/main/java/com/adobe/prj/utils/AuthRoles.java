package com.adobe.prj.utils;

import java.util.ArrayList;
import java.util.Arrays;


public class AuthRoles {
	private ArrayList<String> roles;

	public AuthRoles(String[] roles) {
		this.roles = new ArrayList<String>(Arrays.asList(roles));
	}
	
	// Checks if user is allowed to make the API call
	public Boolean isAuthorized(String role) {
		return this.roles.contains(role);
	}

	public ArrayList<String> getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = new ArrayList<String>(Arrays.asList(roles));
	}




}
