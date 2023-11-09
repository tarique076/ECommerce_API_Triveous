package com.triveous.services;

import com.triveous.exceptions.CartException;
import com.triveous.exceptions.UserException;
import com.triveous.model.Cart;
import com.triveous.model.CartProductDto;

public interface CartServices {

	public Cart addToCart(int productId, int quantity, String username) throws CartException;
	
	public Cart getCart(String username) throws CartException;
	
	public CartProductDto updateQuantities(int productId, int new_quantity, String username) throws CartException, UserException;
	
	public String removeItemFromCart(int productId, String username) throws CartException;
}
