package com.example.demo.services;

import org.springframework.http.ResponseEntity;

import com.example.demo.services.dto.CartDto;

public interface CartService {

	ResponseEntity<?> addCart(String productId);

	ResponseEntity<?> getCart();

}
