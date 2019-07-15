package com.adobe.prj.exceptions;

public class InvalidId extends RuntimeException {
	static String message = "Invalid Id for the Item.";
	
    public InvalidId(String message) {
        super(message);
    }
}

