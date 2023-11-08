package com.triveous.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.triveous.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer>{

}
