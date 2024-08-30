package com.example.demo.services.dto;

import com.example.demo.model.User;

public class TokenResponse {
	
	public User user;
	public String token;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	

}
