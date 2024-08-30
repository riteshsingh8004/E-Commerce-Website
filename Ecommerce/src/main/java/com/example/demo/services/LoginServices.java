package com.example.demo.services;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.User;

public interface LoginServices {

	ResponseEntity<?> addUserDetails(User user);

}
