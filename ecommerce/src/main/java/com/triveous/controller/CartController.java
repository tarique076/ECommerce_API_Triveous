package com.triveous.controller;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.triveous.config.SecurityConstants;
import com.triveous.exceptions.CartException;
import com.triveous.exceptions.UserException;
import com.triveous.model.Cart;
import com.triveous.model.CartProductDto;
import com.triveous.services.CartServices;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("cart")
public class CartController {

	@Autowired
	private CartServices cartService;
	
	@PostMapping("addProduct")
	public ResponseEntity<Cart> addToCart(HttpServletRequest request, @RequestParam int productId, @RequestParam int quantity) throws CartException{
		
		String jwt = request.getHeader(SecurityConstants.JWT_HEADER).substring(7);
		SecretKey key= Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());
		
		Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		
		String username= String.valueOf(claims.get("username"));
		
		Cart cart = cartService.addToCart(productId, quantity, username);
		
		return new ResponseEntity<Cart>(cart, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("get")
	public ResponseEntity<Cart> getCart(HttpServletRequest request) throws CartException{
		
		String jwt = request.getHeader(SecurityConstants.JWT_HEADER).substring(7);
		SecretKey key= Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());
		
		Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		
		String username= String.valueOf(claims.get("username"));
		
		Cart cart = cartService.getCart(username);
		
		return new ResponseEntity<Cart>(cart, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("updateQuantity")
	public ResponseEntity<CartProductDto> updateQuantity(HttpServletRequest request, @RequestParam int productId, @RequestParam int quantity) throws CartException, UserException{
		String jwt = request.getHeader(SecurityConstants.JWT_HEADER).substring(7);
		SecretKey key= Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());
		
		Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		
		String username= String.valueOf(claims.get("username"));
		
		CartProductDto updatedProducts = cartService.updateQuantities(productId, quantity, username);
		
		return new ResponseEntity<CartProductDto>(updatedProducts, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("deleteProduct")
	public ResponseEntity<String> deleteProduct(HttpServletRequest request, @RequestParam int productId) throws CartException{
		String jwt = request.getHeader(SecurityConstants.JWT_HEADER).substring(7);
		SecretKey key= Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes());
		
		Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		
		String username= String.valueOf(claims.get("username"));
		
		String res = cartService.removeItemFromCart(productId, username);
		
		return new ResponseEntity<String>(res, HttpStatus.ACCEPTED);
	}
}
