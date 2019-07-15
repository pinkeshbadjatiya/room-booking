package com.adobe.prj.dao;

import com.adobe.prj.entity.Token;
import com.adobe.prj.entity.User;

public interface TokenDao {
	Token getToken(String api_key);
	Token generateToken(User u);
	void deleteToken(String api_key);
}
