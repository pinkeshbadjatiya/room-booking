package com.adobe.prj.exceptions;

public class NotAuthorized extends RuntimeException {
	static String message = "Not allowed.";
	
    public NotAuthorized(String message) {
        super(message);
    }
}

