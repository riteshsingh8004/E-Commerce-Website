package com.example.demo.services;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.Category;

public interface CategoeyService {

	ResponseEntity<?> addCategory(Category category);

	ResponseEntity<?> getCategory();

	

}
