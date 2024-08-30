package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.repositry.CategoryRepositry;
import com.example.demo.services.CategoeyService;
import com.example.demo.services.util.GenricResponse;

@Service
public class CategoryImpl implements CategoeyService{
	
	@Autowired
	private CategoryRepositry categoryRepositry;

	@Override
	public ResponseEntity<?> addCategory(Category category) {
		// TODO Auto-generated method stub
		
		if(category.getCategoryName()!="") {
			
			List<Category> category1 = categoryRepositry.findAll();
			List<String> categoryName = new ArrayList<>();
			
			category1.forEach(category2 -> {
				categoryName.add(category2.getCategoryName());
			});
			
			if(categoryName.contains(category.getCategoryName())) {
				return ResponseEntity.ok(new GenricResponse(200, "already exist" , null));
			}else {
				categoryRepositry.save(category);
				return ResponseEntity.ok(new GenricResponse(201, "Success", category));
			}
			
		}else {
			return ResponseEntity.ok(new GenricResponse(200, "pls enter category name" , null));
		}
		
	}

	@Override
	public ResponseEntity<?> getCategory() {
		// TODO Auto-generated method stub
		
		List<Category> categories = categoryRepositry.findAll();
		
		return ResponseEntity.ok(new GenricResponse(201, "Success", categories));
	}

}
