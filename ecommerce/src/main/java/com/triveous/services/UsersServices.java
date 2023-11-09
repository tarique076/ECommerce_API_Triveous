package com.triveous.services;

import com.triveous.exceptions.UserException;
import com.triveous.model.Users;

public interface UsersServices {

	public Users registerUser(Users user) throws UserException;
	
	public Users loginUser(Users user) throws UserException;
	
}
