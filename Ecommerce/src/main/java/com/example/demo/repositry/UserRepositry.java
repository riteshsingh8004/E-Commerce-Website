package com.example.demo.repositry;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepositry extends MongoRepository<User, String> {

	User findByEmail(String username);
}
