package com.triveous.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.triveous.dao.UserDao;
import com.triveous.model.Users;

@RestController
public class LoginController {

	@Autowired
	private UserDao userDao;
	
	@GetMapping("/logIn")
	public ResponseEntity<Users> getLoggedInCustomerDetailsHandler(Authentication auth){
		
//		System.err.println(auth);
		
		 Users user= userDao.findByUserName(auth.getName());
		
		 if(user == null) {
			 throw new BadCredentialsException("Invalid username or password");
		 }
		 
		 //to get the token in body, pass HttpServletResponse inside this method parameter 
		// System.out.println(response.getHeaders(SecurityConstants.JWT_HEADER));
		 
		 
		 return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
		
		
	}
}
