package com.example.demo.controller;

// Import necessary classes and annotations
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Import the Category model and CategoeyService class
import com.example.demo.model.Category;
import com.example.demo.services.CategoeyService;

// Define this class as a REST controller
@RestController
// Map all requests to "/api/category" to this controller
@RequestMapping("/api/category")
public class CategoryController {
	
	// Automatically inject an instance of CategoeyService into this controller
	@Autowired
	private CategoeyService categoeyService;
	
	// Define an endpoint for adding a new category
	@PostMapping("/add")
	public ResponseEntity<?> addCategory(@RequestBody Category category){
		// Call the addCategory method from the CategoeyService and return the response
		return categoeyService.addCategory(category);
	}
	
	// Define an endpoint for retrieving all categories
	@GetMapping("/get")
	public ResponseEntity<?> getCategory(){
		// Call the getCategory method from the CategoeyService and return the response
		return categoeyService.getCategory();
	}
}
