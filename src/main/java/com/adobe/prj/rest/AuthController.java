package com.adobe.prj.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.adobe.prj.entity.Token;
import com.adobe.prj.entity.User;
import com.adobe.prj.service.TokenService;
import com.adobe.prj.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
public class AuthController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenService tokenService;
	
	
	@RequestMapping(value="auth/login", method=RequestMethod.POST)
	public ResponseEntity<Token> loginUser(@RequestBody User u) {
		if (!userService.isValidUser(u)) {
			// TODO: Error, invalid password
		}
		User ref_user = userService.getUserByEmail(u.getEmail());
		System.out.println(ref_user);
		Token token = tokenService.generateToken(ref_user);
		System.out.println(token);
		return new ResponseEntity<>(token, HttpStatus.CREATED);
	}

	@RequestMapping(value="auth/logout", method=RequestMethod.DELETE)
	public ResponseEntity<String> logoutUser(@RequestBody Token tk) {
		
		
//		//convert json string to object
//		ObjectMapper objectMapper = new ObjectMapper();
//		String api_key = null;
//		Map<String,String> myMap = new HashMap<String, String>();
//		try {
//			myMap = objectMapper.readValue(json_data, HashMap.class);
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		api_key = myMap.get("api_key");
		String api_key = tk.getApiKey();
		
		
		if (api_key == null) {
			// TODO: Error, no API_KEY found
		}
		Token ref_token = tokenService.getToken(api_key);
		if (ref_token == null) {
			// TODO: Error, invalid token
		}
		tokenService.deleteToken(api_key);
		return new ResponseEntity<>("{'deleted': 'OK'}", HttpStatus.OK);
	}
	
	
}
