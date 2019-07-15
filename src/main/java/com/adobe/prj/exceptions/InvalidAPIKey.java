package com.adobe.prj.exceptions;

public class InvalidAPIKey extends RuntimeException {
	static String message = "Invalid API Key";
	
    public InvalidAPIKey(String message) {
        super(message);
    }
}

