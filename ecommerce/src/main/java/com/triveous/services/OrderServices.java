package com.triveous.services;

import java.util.List;

import com.triveous.exceptions.OrdersException;
import com.triveous.model.Orders;

public interface OrderServices {

	public Orders placeOrder(String username) throws OrdersException;
	
	public Orders getOrderById(int orderId, String username) throws OrdersException;
	
	public List<Orders> getOrderHistory(String username) throws OrdersException;
}
