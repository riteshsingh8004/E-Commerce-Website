package com.example.demo.services.dto;

import java.util.List;

import com.example.demo.model.Product;

public class CartDetailResponse {
	
	private String id;
	private List<Product> product;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	
	
	
	
	
	
	

}
