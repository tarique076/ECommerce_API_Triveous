package com.triveous.services;

import org.springframework.stereotype.Service;

import com.triveous.exceptions.CartException;
import com.triveous.model.Cart;

@Service
public class CartServicesImpl implements CartServices{

	@Override
	public Cart addToCart(int productId, int quantity) throws CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart updateQuantities(int productId, int new_quantity) throws CartException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart removeItemFromCart(int productId) throws CartException {
		// TODO Auto-generated method stub
		return null;
	}

}
