package com.example.demo.controller;

// Import necessary classes and annotations
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Import the CartService class and CartDto class
import com.example.demo.services.CartService;
import com.example.demo.services.dto.CartDto;

// Define this class as a REST controller
@RestController
// Map all requests to "/api/cart" to this controller
@RequestMapping("/api/cart")
public class CartController {
	
	// Automatically inject an instance of CartService into this controller
	@Autowired
	private CartService cartService;
	
	// Define an endpoint for adding an item to the cart
	@PostMapping("/add")
	public ResponseEntity<?> addCart(@RequestParam("productId") String productId){
		// Call the addCart method from the CartService and return the response
		return cartService.addCart(productId);
	}
	
	// Define an endpoint for retrieving the cart
	@GetMapping("/get")
	public ResponseEntity<?> getCart(){
		// Call the getCart method from the CartService and return the response
		return cartService.getCart();
	}
}
