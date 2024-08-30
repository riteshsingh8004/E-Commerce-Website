package com.example.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cart;
import com.example.demo.model.User;
import com.example.demo.repositry.CartRepositry;
import com.example.demo.repositry.UserRepositry;
import com.example.demo.services.LoginServices;
import com.example.demo.services.util.GenricResponse;

@Service
public class LoginImpl implements LoginServices {
    // Marks this class as a Spring service component, enabling it to be detected and managed by Spring's container

    @Autowired
    private UserRepositry userRepositry;
    // Automatically injects an instance of UserRepositry into this class

    @Autowired
    private CartRepositry cartRepositry;
    // Automatically injects an instance of CartRepositry into this class

    @Override
    public ResponseEntity<?> addUserDetails(User user) {
        // Implements the addUserDetails method from the LoginServices interface

        // Extract the email from the User object
        String email = user.getEmail();

        // Fetch the user from the repository by email
        User user1 = userRepositry.findByEmail(email);

        // Check if the user with the given email already exists
        if (user1 == null) {
            // If the user does not exist, save the new user to the repository
            userRepositry.save(user);

            // Create a new Cart object and associate it with the new user by setting the user ID
            Cart cart = new Cart();
            cart.setUserId(user.getId());

            // Save the new Cart object to the repository
            cartRepositry.save(cart);

            // Return a success response with HTTP status 201 (Created) and the new user details
            return ResponseEntity.ok(new GenricResponse(201, "Success", user));
        } else {
            // If the user already exists, return a response indicating that the email is already in use
            return ResponseEntity.ok(new GenricResponse(203, "Sorry Email id already exist", null));
        }
    }
}
