package com.triveous.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.triveous.model.CartProductDto;

public interface CartProductDtoDao extends JpaRepository<CartProductDto, Integer>{

}
