package com.triveous.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triveous.dao.CategoryDao;
import com.triveous.dao.ProductDao;
import com.triveous.exceptions.CategoryException;
import com.triveous.exceptions.ProductException;
import com.triveous.model.Category;
import com.triveous.model.Product;

@Service
public class ProductServicesImpl implements ProductServices{

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public Product addProduct(Product product) throws ProductException {
		
		if(product.getCategory()==null) {
			throw new ProductException("Please specify a category for the product");
		}

		product.getCategory().getProducts().add(product);
		
		
		return productDao.save(product);
	}

	@Override
	public List<Product> getProductsByCategoryId(int categoryId) throws ProductException, CategoryException {
		// TODO Auto-generated method stub
		
		Optional<Category> categoryOpt = categoryDao.findById(categoryId);
		
		if(categoryOpt.isEmpty()) {
			throw new CategoryException("Invalid categoryId provided. " +categoryId);
		}
		
		List<Product> products = categoryOpt.get().getProducts();
		if(products==null || products.isEmpty()) {
			throw new ProductException("No products available in this category right now.!");
		}
		return products;
	}

	@Override
	public Product getProductById(int productId) throws ProductException {

		Optional<Product> productOpt = productDao.findById(productId);
		
		if(productOpt.isEmpty()) {
			throw new ProductException("No product found with productId: "+productId+" . Please enter a valid productId.!");
		}
		return productOpt.get();
	}

}
