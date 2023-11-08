package com.triveous.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.triveous.exceptions.CategoryException;
import com.triveous.exceptions.ProductException;
import com.triveous.model.Product;
import com.triveous.services.ProductServices;

@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	private ProductServices productService;
	
	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) throws ProductException{
		Product savedProduct = productService.addProduct(product);
		
		return new ResponseEntity<Product>(savedProduct, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("getByCategory")
	public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam int categoryId) throws ProductException, CategoryException{
		
		List<Product> products = productService.getProductsByCategoryId(categoryId);
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}
	
	@GetMapping("getByProductId")
	public ResponseEntity<Product> getProductById(@RequestParam int productId) throws ProductException{
		
		Product product = productService.getProductById(productId);
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
}
