package com.triveous.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triveous.dao.CategoryDao;
import com.triveous.exceptions.CategoryException;
import com.triveous.model.Category;

@Service
public class CategoryServicesImpl implements CategoryServices{

	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public List<Category> getAllCategories() throws CategoryException {

		List<Category> categories = categoryDao.findAll();
		
		if(categories.isEmpty()) {
			throw new CategoryException("No categories available at the moment.!");
		}
		
		return categories;
	}

	@Override
	public Category addCategory(Category category) throws CategoryException {
		// TODO Auto-generated method stub
		
		Category existingCategory = categoryDao.findByCategoryName(category.getCategoryName());
		
		if(existingCategory != null) {
			throw new CategoryException("Category "+category.getCategoryName()+" already exists.!");
		}
		return categoryDao.save(category);
	}

}
