package com.adobe.prj.exceptions;

public class DeletionNotPossible extends RuntimeException {
	static String message = "Invalid deletion request.";
	
    public DeletionNotPossible(String message) {
        super(message);
    }
}

