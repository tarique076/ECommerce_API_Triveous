package com.triveous.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.triveous.model.Cart;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer>{

}
