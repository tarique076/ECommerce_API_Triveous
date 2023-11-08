package com.triveous.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.triveous.exceptions.CategoryException;
import com.triveous.model.Category;
import com.triveous.services.CategoryServices;

@RestController
@RequestMapping("category")
public class CategoryController {
	
	@Autowired
	private CategoryServices categoryService;

	@GetMapping("/getAll")
	public ResponseEntity<List<Category>> getAllCategories() throws CategoryException{
		
		List<Category> categories = categoryService.getAllCategories();
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) throws CategoryException{
		
		Category savedCategory = categoryService.addCategory(category);
		
		return new ResponseEntity<Category>(savedCategory, HttpStatus.ACCEPTED);
	}
}
