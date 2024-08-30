package com.example.demo.controller;

// Import necessary classes and annotations
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.services.ProductService;

// Define this class as a REST controller
@RestController
// Map all requests to "/product/api" to this controller
@RequestMapping("/product/api")
public class ProductController {
	
	// Automatically inject an instance of ProductService
	@Autowired
	private ProductService productService;
	
	// Define an endpoint for adding a new product (POST request to "/product/api/{categoryId}/add")
	@PostMapping("/{categoryId}/add")
	public ResponseEntity<?> addProduct(@RequestBody Product product, @PathVariable("categoryId") String categoryId){
		// Set the category ID of the product
		product.setCategory(categoryId);
		// Call the addProduct method from ProductService to save the product and return the response
		return productService.addProduct(product);
	}
	
	// Define an endpoint for retrieving all products (GET request to "/product/api/getall")
	@GetMapping("/getall")
	public ResponseEntity<?> getProduct(){
		// Call the getProduct method from ProductService and return the response
		return productService.getProduct();
	}
	
	// Define an endpoint for retrieving products by category ID (GET request to "/product/api/get/{categoryId}")
	@GetMapping("/get/{categoryId}")
	public ResponseEntity<?> getProductByCategory(@PathVariable("categoryId") String categoryId){
		// Call the getProductByCategory method from ProductService with the given category ID and return the response
		return productService.getProductByCategory(categoryId);
	}
	
	// Define an endpoint for retrieving a product by product ID (GET request to "/product/api/get")
	@GetMapping("/get")
	public ResponseEntity<?> getProductById(@RequestParam("productId") String productId){
		// Call the getProductById method from ProductService with the given product ID and return the response
		return productService.getProductByTd(productId);
	}
	
}
