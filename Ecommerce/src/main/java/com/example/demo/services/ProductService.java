package com.example.demo.services;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.Product;

public interface ProductService {

	ResponseEntity<?> addProduct(Product product);

	ResponseEntity<?> getProduct();


	ResponseEntity<?> getProductByCategory(String categoryId);

	ResponseEntity<?> getProductByTd(String productId);

}
