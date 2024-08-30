package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repositry.CartRepositry;
import com.example.demo.repositry.ProductRepositry;
import com.example.demo.repositry.UserRepositry;
import com.example.demo.services.CartService;
import com.example.demo.services.dto.CartDetailResponse;
import com.example.demo.services.dto.CartDto;
import com.example.demo.services.util.GenricResponse;

@Service
public class CartImpl implements CartService {
	
	@Autowired
	private CartRepositry cartRepositry;
	
	@Autowired
	private UserRepositry userRepositry;
	
	@Autowired
	private ProductRepositry productRepositry;

	@Override
	public ResponseEntity<?> addCart(String productId) {
		// TODO Auto-generated method stub
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = auth.getName();
		
		User user = userRepositry.findByEmail(userEmail);
		System.out.println(user.getId());
		
		Cart cart = cartRepositry.findByUserId(user.getId());
		List<String> productId1 =  new ArrayList<>();
		
		if(cart.getProductId() == null) {
			productId1.add(productId);
		}else {
			productId1.addAll(cart.getProductId());
			productId1.add(productId);
		}
		
		
		
		cart.setId(cart.getId());
		cart.setUserId(cart.getUserId());
		cart.setProductId(productId1);
		
		cartRepositry.save(cart);
		
		return ResponseEntity.ok(new GenricResponse(201, "Success", cart));
	}

	@Override
	public ResponseEntity<?> getCart() {
		// TODO Auto-generated method stub
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = auth.getName();
		
		User user = userRepositry.findByEmail(userEmail);
		System.out.println(user.getId());
		
		Cart cart = cartRepositry.findByUserId(user.getId());
		
		if (cart.getProductId() == null) {
			
			return ResponseEntity.ok(new GenricResponse(201, "Cart is empty", null));
		}else {
			
			CartDetailResponse cartDetailResponse = new CartDetailResponse();
			
			List<String> productList = cart.getProductId();
			
			List<Product> product = new ArrayList<>();
			
			productList.forEach(productList1 -> {
				Product product1 = productRepositry.findById(productList1).get();
				
				product.add(product1);
			});
			cartDetailResponse.setId(cart.getId());
			cartDetailResponse.setProduct(product);
			return ResponseEntity.ok(new GenricResponse(201, "Success", cartDetailResponse));
		}
		
		
	}



}
