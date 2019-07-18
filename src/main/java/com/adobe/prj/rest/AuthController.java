package com.adobe.prj.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adobe.prj.entity.Token;
import com.adobe.prj.entity.User;
import com.adobe.prj.exceptions.InvalidAPIKey;
import com.adobe.prj.exceptions.InvalidParameterOrMissingValue;
import com.adobe.prj.service.TokenService;
import com.adobe.prj.service.UserService;


@CrossOrigin(maxAge = 3600)
@RestController
public class AuthController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenService tokenService;
	
	
	@RequestMapping(value="auth/login", method=RequestMethod.POST)
	public ResponseEntity<Token> loginUser(@RequestBody User u) {
		if (!userService.isValidUser(u)) {
			
			throw new InvalidParameterOrMissingValue("invalid password");
		}
		User ref_user = userService.getUserByEmail(u.getEmail());
		System.out.println(ref_user);
		Token token = tokenService.generateToken(ref_user);
		System.out.println(token);
		return new ResponseEntity<>(token, HttpStatus.CREATED);
	}

	@RequestMapping(value="auth/logout", method=RequestMethod.DELETE)
	public ResponseEntity<String> logoutUser(@RequestBody Token tk) {
		
		String api_key = tk.getApiKey();
		
		
		if (api_key == null) {
			throw new InvalidAPIKey("Not a valid API_KEY");
		}
		Token ref_token = tokenService.getToken(api_key);
		if (ref_token == null) {
			throw new InvalidParameterOrMissingValue("Not a valid token");
		}
		tokenService.deleteToken(api_key);
		return new ResponseEntity<>("{'deleted': 'OK'}", HttpStatus.OK);
	}
	
	
}
