package com.example.demo.controller;

// Import necessary classes and annotations
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Import the User model, repository, and various service classes
import com.example.demo.model.User;
import com.example.demo.repositry.UserRepositry;
import com.example.demo.services.LoginServices;
import com.example.demo.services.dto.LoginDto;
import com.example.demo.services.dto.TokenResponse;
import com.example.demo.services.dto.UserDetails;
import com.example.demo.services.util.GenricResponse;
import com.example.demo.services.util.JwtUtil;
import com.example.demo.services.util.UserDetailsInfo;

// Define this class as a REST controller
@RestController
// Map all requests to "/api" to this controller
@RequestMapping("/api")
public class LoginController {
	
	// Automatically inject an instance of AuthenticationManager
	@Autowired
	private AuthenticationManager authenticationManager;
	
	// Automatically inject an instance of JwtUtil
	@Autowired
	private JwtUtil jwtUtil;
	
	// Automatically inject an instance of LoginServices
	@Autowired
	private LoginServices loginServices;
	
	// Automatically inject an instance of BCryptPasswordEncoder
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// Automatically inject an instance of UserRepositry
	@Autowired
	private UserRepositry userRepositry;
	
	// Define an endpoint for login (GET request to "/api/login")
	@GetMapping("/login")
	public ResponseEntity<?> login(){
		
		// Create a new instance of LoginDto
		LoginDto loginDto = new LoginDto();
		// loginDto.setUsername("Chirag"); // Commented out line to set a username (if needed)
		//loginDto.setPassword("5673"); // Set a dummy password for demonstration
		
		// Return a successful response with the loginDto object
		return ResponseEntity.ok(new GenricResponse(201, "Success", loginDto));
	}
	
	// Define an endpoint for signup (POST request to "/api/signup")
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody User user){
		
		// Encode the user's password using BCryptPasswordEncoder
		String password = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(password); // Set the encoded password
		
		// Call the addUserDetails method from LoginServices to save the user and return the response
		return loginServices.addUserDetails(user);
	}
	
	// Define an endpoint for authentication (GET request to "/api/auth")
	@GetMapping("/auth")
	public ResponseEntity<?> auth(@RequestBody LoginDto loginDto) throws Exception {
		
		// Authenticate the user using the provided email and password
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
		);
		
		// Check if the authentication was successful
		if (authentication.isAuthenticated()) {
			 
			// Create a new instance of TokenResponse
			TokenResponse tokenResponse = new TokenResponse();
			 
			// Retrieve the User object by email
			User user = userRepositry.findByEmail(loginDto.getEmail());
			 
			// Set the user and generated token in the tokenResponse object
			tokenResponse.setUser(user);
			tokenResponse.setToken(jwtUtil.generateToken(loginDto.getEmail()));
			 
			// Return a successful response with the tokenResponse object
			return ResponseEntity.ok(new GenricResponse(201, "Success", tokenResponse));
		} else {
			// Throw an exception if authentication fails
			throw new UsernameNotFoundException("Invalid user request!");
		} 
	}
	
	// Define an endpoint to check the current user's authentication (GET request to "/api/check")
	@GetMapping("/check")
	public ResponseEntity<?> check() {
		
		// Get the current authentication object from the SecurityContext
		Authentication user = SecurityContextHolder.getContext().getAuthentication();
		
		// Retrieve the UserDetailsInfo object from the authentication principal
		UserDetailsInfo user1 = (UserDetailsInfo) user.getPrincipal();
		
		// Create a new instance of UserDetails and populate it with user information
		UserDetails userDetails = new UserDetails();
		userDetails.setId(user1.getUser().getId());
		userDetails.setEmail(user1.getUser().getEmail());
		
		// Return a successful response with the userDetails object
		return ResponseEntity.ok(new GenricResponse(201, "Success", userDetails));
	}
}
