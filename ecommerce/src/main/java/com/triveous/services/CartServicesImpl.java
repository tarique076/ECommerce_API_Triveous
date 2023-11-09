package com.triveous.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triveous.dao.CartDao;
import com.triveous.dao.CartProductDtoDao;
import com.triveous.dao.ProductDao;
import com.triveous.dao.UserDao;
import com.triveous.exceptions.CartException;
import com.triveous.exceptions.UserException;
import com.triveous.model.Cart;
import com.triveous.model.CartProductDto;
import com.triveous.model.Product;
import com.triveous.model.Users;

@Service
public class CartServicesImpl implements CartServices {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private CartDao cartDao;

	@Autowired
	private CartProductDtoDao cartProductDao;

	@Autowired
	private UserDao userDao;

	@Override
	public Cart addToCart(int productId, int quantity, String username) throws CartException {

		Users user = userDao.findByUserName(username);

		if (user == null) {
			throw new CartException("Please login first to add to Cart");
		}

		Product product = productDao.findById(productId)
				.orElseThrow(() -> new CartException("No product found with id: " + productId));

		CartProductDto dto = new CartProductDto();

		dto.setProductId(product.getProductId());
		dto.setTitle(product.getTitle());
		if (product.getAvailability() > quantity)
			dto.setQuantity(quantity);
		else
			throw new CartException("Only " + product.getAvailability() + " products left with id " + productId);

		dto.setPrice(product.getPrice());


		if (user.getCart() == null) {
			Cart newCart = new Cart();
			newCart.getCartProducts().add(dto);
			newCart.setQuantity(quantity);
			newCart.setTotalCost(dto.getPrice() * dto.getQuantity());
			user.setCart(newCart);
		} else {
			boolean existsInCart = false;

			for (CartProductDto cartProduct : user.getCart().getCartProducts()) {
				if (dto.getTitle().equals(cartProduct.getTitle()))
					existsInCart = true;
			}

			if (existsInCart) {
				throw new CartException(
						"Product already in cart. If you want to update quantiy, please use update quantity option.!");
			}
			
			user.getCart().setQuantity(quantity + user.getCart().getQuantity());
			user.getCart().setTotalCost(user.getCart().getTotalCost() + (dto.getPrice() * dto.getQuantity()));
			user.getCart().getCartProducts().add(dto);
		}

		userDao.save(user);

		return cartDao.save(user.getCart());
	}

	@Override
	public CartProductDto updateQuantities(int productId, int new_quantity, String username) throws CartException, UserException {
		// TODO Auto-generated method stub
		
		Users user = userDao.findByUserName(username);
				
		if(user == null) {
			throw new UserException("Please login first.!");
		}
		
		if (user.getCart() == null)
			throw new CartException("No items added in cart.");

		CartProductDto dto = cartProductDao.findById(productId)
				.orElseThrow(() -> new CartException("No product found in cart with productId " + productId));
		
		dto.setQuantity(new_quantity);
		
		userDao.save(user);
		
		return cartProductDao.save(dto);
	}

	@Override
	public String removeItemFromCart(int productId, String username) throws CartException {
		
		Users user = userDao.findByUserName(username);

		if (user == null) {
			throw new CartException("Please login first to check your Cart");
		}
		
		if(user.getCart() == null) {
			throw new CartException("Nothing added to cart.!");
		}
		
		CartProductDto dto = cartProductDao.findById(productId)
				.orElseThrow(() -> new CartException("No product found in cart with productId " + productId));

		user.getCart().getCartProducts().remove(dto);

		if (user.getCart().getCartProducts().isEmpty()) {
			Cart cart = user.getCart();
			cartDao.delete(cart);
			user.setCart(null);
		}

		userDao.save(user);

		return "Product deleted from cart.";
	}

	@Override
	public Cart getCart(String username) throws CartException {

		Users user = userDao.findByUserName(username);

		if (user == null) {
			throw new CartException("Please login first to check your Cart");
		}
		
		if(user.getCart() == null) {
			throw new CartException("Nothing added to cart.!");
		}
		return user.getCart();
	}

}
