package com.example.demo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repositry.CategoryRepositry;
import com.example.demo.repositry.ProductRepositry;
import com.example.demo.services.ProductService;
import com.example.demo.services.util.GenricResponse;
@Service
//Indicates that this class is a service component in the Spring framework. 
//The `@Service` annotation is used to define a Spring service bean.

public class ProductImpl implements ProductService {
//Declares the `ProductImpl` class, which implements the `ProductService` interface.

 @Autowired
 // The `@Autowired` annotation is used to inject the `ProductRepositry` bean into this service class.
 private ProductRepositry productRepositry;
 // Declares a private field `productRepositry` of type `ProductRepositry` to perform database operations related to products.

 @Autowired 
 // The `@Autowired` annotation is used to inject the `CategoryRepositry` bean into this service class.
 private CategoryRepositry categoryRepositry;
 // Declares a private field `categoryRepositry` of type `CategoryRepositry` to perform database operations related to categories.

 @Override
 // The `@Override` annotation indicates that this method is overriding a method defined in the `ProductService` interface.
 public ResponseEntity<?> addProduct(Product product) {
     // This method handles adding a new product to the database.

     Product product1 = new Product();
     // Creates a new `Product` object named `product1`.

     productRepositry.save(product);
     // Saves the `product` object to the database using the `productRepositry`.

     String categoryId = product.getCategory();
     // Retrieves the category ID from the `product` object.

     Category category = categoryRepositry.findById(categoryId).get();
     // Fetches the `Category` object from the database using the category ID.

     product1.setTitle(product.getTitle());
     // Sets the title of `product1` to the title of the `product` object.

     product1.setCategory(category.getCategoryName());
     // Sets the category of `product1` to the name of the retrieved category.

     product1.setDescription(product.getDescription());
     // Sets the description of `product1` to the description of the `product` object.

     product1.setId(product.getId());
     // Sets the ID of `product1` to the ID of the `product` object.

     product1.setPrice(product.getPrice());
     // Sets the price of `product1` to the price of the `product` object.

     product1.setRating(product.getRating());
     // Sets the rating of `product1` to the rating of the `product` object.

     product1.setStock(product.getStock());
     // Sets the stock of `product1` to the stock of the `product` object.

     return ResponseEntity.ok(new GenricResponse(201, "Success", product1));
     // Returns a successful HTTP response (201 status) with a generic response object containing the newly added product details.
 }

 @Override
 // Indicates that this method is overriding the `getProduct` method from the `ProductService` interface.
 public ResponseEntity<?> getProduct() {
     // This method retrieves all products from the database.

     List<Product> product = productRepositry.findAll();
     // Fetches all products from the database using `productRepositry` and stores them in a list.

     product.forEach(product1 -> {
         String categoryId = product1.getCategory();
         // For each product, retrieves the category ID.

         Category category = categoryRepositry.findById(categoryId).get();
         // Fetches the `Category` object corresponding to the category ID.

         product1.setCategory(category.getCategoryName());
         // Sets the category name of the product.
     });

     return ResponseEntity.ok(new GenricResponse(201, "Success", product));
     // Returns a successful HTTP response (201 status) with a generic response object containing the list of all products.
 }

 @Override
 // Indicates that this method is overriding the `getProductByCategory` method from the `ProductService` interface.
 public ResponseEntity<?> getProductByCategory(String categoryId) {
     // This method retrieves products based on a specific category.

     List<Product> product = productRepositry.findByCategory(categoryId);
     // Fetches products that belong to a specific category using `productRepositry`.

     product.forEach(product1 -> {
         String categoryId1 = product1.getCategory();
         // For each product, retrieves the category ID.

         Category category = categoryRepositry.findById(categoryId1).get();
         // Fetches the `Category` object corresponding to the category ID.

         product1.setCategory(category.getCategoryName());
         // Sets the category name of the product.
     });

     return ResponseEntity.ok(new GenricResponse(201, "Success", product));
     // Returns a successful HTTP response (201 status) with a generic response object containing the list of products for the specified category.
 }

 @Override
 // Indicates that this method is overriding the `getProductByTd` method from the `ProductService` interface.
 public ResponseEntity<?> getProductByTd(String productId) {
     // This method retrieves a product based on its ID.

     Product product = productRepositry.findById(productId).get();
     // Fetches the `Product` object from the database using the product ID.

     Category category = categoryRepositry.findById(product.getCategory()).get();
     // Fetches the `Category` object corresponding to the product's category.

     product.setCategory(category.getCategoryName());
     // Sets the category name of the product.

     return ResponseEntity.ok(new GenricResponse(201, "Success", product));
     // Returns a successful HTTP response (201 status) with a generic response object containing the product details.
 }
}
