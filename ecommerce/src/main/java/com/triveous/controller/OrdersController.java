package com.triveous.controller;

import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.triveous.config.SecurityConstants;
import com.triveous.exceptions.OrdersException;
import com.triveous.model.Orders;
import com.triveous.services.OrderServices;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("orders")
public class OrdersController {

	@Autowired
	private OrderServices orderService;
	
	@PostMapping("/place")
	public ResponseEntity<Orders> placeOrder(HttpServletRequest request) throws OrdersException{
		String jwt = request.getHeader(SecurityConstants.JWT_HEADER).substring(7);
		SecretKey key= Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());
		
		Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		
		String username= String.valueOf(claims.get("username"));
		
		Orders newOrder = orderService.placeOrder(username);
		
		return new ResponseEntity<Orders>(newOrder, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/findById")
	public ResponseEntity<Orders> getOrderById(HttpServletRequest request, @RequestParam int orderId) throws OrdersException{
		String jwt = request.getHeader(SecurityConstants.JWT_HEADER).substring(7);
		SecretKey key= Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());
		
		Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		
		String username= String.valueOf(claims.get("username"));
		
		Orders order = orderService.getOrderById(orderId, username);
		
		return new ResponseEntity<Orders>(order, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/history")
	public ResponseEntity<List<Orders>> getOrderHistory(HttpServletRequest request) throws OrdersException{
		String jwt = request.getHeader(SecurityConstants.JWT_HEADER).substring(7);
		SecretKey key= Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());
		
		Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		
		String username= String.valueOf(claims.get("username"));
		
		List<Orders> order = orderService.getOrderHistory(username);
		
		return new ResponseEntity<List<Orders>>(order, HttpStatus.ACCEPTED);
	}
}
