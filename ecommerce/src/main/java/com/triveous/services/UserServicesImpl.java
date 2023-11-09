package com.triveous.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triveous.dao.UserDao;
import com.triveous.exceptions.UserException;
import com.triveous.model.Authority;
import com.triveous.model.Users;

@Service
public class UserServicesImpl implements UsersServices{

	@Autowired
	private UserDao userDao;
	
	@Override
	public Users registerUser(Users user) throws UserException {
		
		if(userDao.findByUserName(user.getUserName()) != null ) {
			throw new UserException("Username already exists!");
		}
		
		List<Authority> authorities= user.getAuthorities();
		
		for(Authority authority:authorities) {
			authority.setUsers(user);
		}
		
		return userDao.save(user);
	}

	@Override
	public Users loginUser(Users user) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

}
