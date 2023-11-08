package com.triveous.services;

import com.triveous.exceptions.CartException;
import com.triveous.model.Cart;

public interface CartServices {

	public Cart addToCart(int productId, int quantity) throws CartException;
	
	public Cart updateQuantities(int productId, int new_quantity) throws CartException;
	
	public Cart removeItemFromCart(int productId) throws CartException;
}
