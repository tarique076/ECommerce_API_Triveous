package com.triveous.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.triveous.model.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer>{

	public Category findByCategoryName(String categoryName);
}
