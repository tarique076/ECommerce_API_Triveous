package com.triveous.services;

import java.util.List;

import com.triveous.exceptions.CategoryException;
import com.triveous.exceptions.ProductException;
import com.triveous.model.Product;

public interface ProductServices {

	public Product addProduct(Product product) throws ProductException;
	
	public List<Product> getProductsByCategoryId(int categoryId) throws ProductException, CategoryException;
	
	public Product getProductById(int productId) throws ProductException;
}
