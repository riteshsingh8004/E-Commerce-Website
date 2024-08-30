package com.example.demo.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "category")
public class Category {
	
	private String id;
	private String categoryName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	

}
