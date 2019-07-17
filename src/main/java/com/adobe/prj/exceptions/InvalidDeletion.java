package com.adobe.prj.exceptions;

public class InvalidDeletion extends RuntimeException {
	static String message = "Invalid Deletion Request. Please try with a valid ID/Value.";
	
    public InvalidDeletion(String message) {
        super(message);
    }
}

