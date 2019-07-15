package com.adobe.prj.exceptions;

import java.sql.SQLException;

import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.adobe.prj.entity.User;
import com.fasterxml.jackson.databind.util.JSONPObject;




@ControllerAdvice
public class ServiceErrorAdvice extends ResponseEntityExceptionHandler{
	
	public String generateJSON(String error, String message, HttpStatus status) {
		JSONObject obj = new JSONObject();    
		obj.put("error", error);
		obj.put("message", message);
		obj.put("status", new Integer(status.toString()));
    	return obj.toString();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({SQLException.class, NullPointerException.class})
    public ResponseEntity<String> handle1(Exception e) {
    	return new ResponseEntity<>(generateJSON("Something went wrong at the BACKend, try again later", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidAPIKey.class})
    public ResponseEntity<String> handle2(Exception e) {
    	return new ResponseEntity<>(generateJSON(InvalidAPIKey.message, e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }
    
//    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
//        String error = "Invalid API Key";
//        return handleExceptionInternal(ex, generateJSON(error, HttpStatus.BAD_REQUEST), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//    }
}