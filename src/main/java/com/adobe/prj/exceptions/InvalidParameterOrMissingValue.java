package com.adobe.prj.exceptions;


public class InvalidParameterOrMissingValue extends RuntimeException {
	static String message = "Invalid or Missing value";
	
    public InvalidParameterOrMissingValue(String message) {
        super(message);
    }
}
