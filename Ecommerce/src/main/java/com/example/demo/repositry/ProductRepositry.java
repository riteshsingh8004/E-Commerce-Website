package com.example.demo.repositry;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

@Repository
public interface ProductRepositry extends MongoRepository<Product, String>{

	List<Product> findByCategory(String categoryId);

}
