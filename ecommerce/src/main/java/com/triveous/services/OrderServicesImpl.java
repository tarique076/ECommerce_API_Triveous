package com.triveous.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.triveous.dao.CartDao;
import com.triveous.dao.OrdersDao;
import com.triveous.dao.UserDao;
import com.triveous.exceptions.OrdersException;
import com.triveous.model.Orders;
import com.triveous.model.Users;

@Service
public class OrderServicesImpl implements OrderServices{

	@Autowired
	private OrdersDao orderDao;
	
	@Autowired 
	private CartDao cartDao;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public Orders placeOrder(String username) throws OrdersException {
		
		Users user = userDao.findByUserName(username);

		if (user == null) {
			throw new OrdersException("Please login first to check your Cart");
		}
		
		if(user.getCart() == null) {
			throw new OrdersException("Nothing added to cart to place an order.!");
		}
		
		Orders newOrder = new Orders();
		newOrder.setCart(user.getCart());
		newOrder.setUser(user);
		
		user.getOrders().add(newOrder);
		
		user.setCart(null);
		
		return orderDao.save(newOrder);
	}

	@Override
	public Orders getOrderById(int orderId, String username) throws OrdersException {
		
		Users user = userDao.findByUserName(username);

		if (user == null) {
			throw new OrdersException("Please login first to check your Cart");
		}
		
		Optional<Orders> orderOpt = orderDao.findById(orderId);
		
		if(orderOpt.isEmpty()) {
			throw new OrdersException("No orders found with order id: " + orderId);
		}
		
		return orderOpt.get();
	}

	@Override
	public List<Orders> getOrderHistory(String username) throws OrdersException {
		
		Users user = userDao.findByUserName(username);

		if (user == null) {
			throw new OrdersException("Please login first to check your Cart");
		}
		
		if(user.getOrders().isEmpty()) {
			throw new OrdersException("No orders placed yet.!");
		}
		
		List<Orders> orders = user.getOrders();
		
		return orders;
	}

}
