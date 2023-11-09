package com.triveous.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.triveous.exceptions.UserException;
import com.triveous.model.Users;
import com.triveous.services.UsersServices;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UsersServices userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<Users> registerUser(@RequestBody Users user) throws UserException{
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Users registeredUser = userService.registerUser(user);
		
		return new ResponseEntity<Users>(registeredUser, HttpStatus.ACCEPTED);
	}
	
}
