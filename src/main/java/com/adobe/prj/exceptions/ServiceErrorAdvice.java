package com.adobe.prj.exceptions;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;
import org.json.simple.JSONObject;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mysql.cj.jdbc.exceptions.SQLError;

@ControllerAdvice
public class ServiceErrorAdvice extends ResponseEntityExceptionHandler {

	public String generateJSON(String error, String message, HttpStatus status) {
		JSONObject obj = new JSONObject();
		obj.put("error", error);
		obj.put("message", message);
		obj.put("status", new Integer(status.toString()));
		return obj.toString();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ InvalidAPIKey.class })
	public ResponseEntity<String> handle1(Exception e) {
		return new ResponseEntity<>(generateJSON(InvalidAPIKey.message, e.getMessage(), HttpStatus.BAD_REQUEST),
				HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler({ NotAuthorized.class })
	public ResponseEntity<String> handle2(Exception e) {
		return new ResponseEntity<>(generateJSON(NotAuthorized.message, e.getMessage(), HttpStatus.FORBIDDEN),
				HttpStatus.FORBIDDEN);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ InvalidId.class })
	public ResponseEntity<String> handle3(Exception e) {
		return new ResponseEntity<>(generateJSON(InvalidId.message, e.getMessage(), HttpStatus.BAD_REQUEST),
				HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({DataIntegrityViolationException.class, SQLException.class, SQLIntegrityConstraintViolationException.class, ConstraintViolationException.class, PersistenceException.class})
	public ResponseEntity<String> handle4(SQLException e) {
		System.out.println(e.getErrorCode());
		System.out.println(e.getMessage());
		return new ResponseEntity<>(generateJSON(InvalidDeletion.message, e.getMessage(), HttpStatus.BAD_REQUEST),
				HttpStatus.BAD_REQUEST);
	}
	
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
//	@ExceptionHandler({ })
//	public ResponseEntity<String> handle5(Exception e) {
//		return new ResponseEntity<>(generateJSON(InvalidDeletion.message, e.getMessage(), HttpStatus.BAD_REQUEST),
//				HttpStatus.BAD_REQUEST);
//	}
//
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({ InvalidAPIKey.class })
	protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
		return new ResponseEntity<>(generateJSON("Invalid API_KEY", ex.getMessage(),
				HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
	 }

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Exception.class, NullPointerException.class })
	public ResponseEntity<String> handle_generic(Exception e) {
		return new ResponseEntity<>(generateJSON("Something went wrong at the BACKend, try again later", e.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}