package com.triveous.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.triveous.model.Category;

@RestController
@RequestMapping("category")
public class CategoryController {

	@GetMapping("/getAll")
	public ResponseEntity<List<Category>> getAllCategories(){
		
		return new ResponseEntity<List<Category>>(new ArrayList<>(), null);
	}
}
