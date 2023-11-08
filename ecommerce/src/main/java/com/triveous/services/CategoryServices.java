package com.triveous.services;

import java.util.List;

import com.triveous.exceptions.CategoryException;
import com.triveous.model.Category;

public interface CategoryServices {

	public List<Category> getAllCategories() throws CategoryException;
	
	public Category addCategory(Category category) throws CategoryException;
}
