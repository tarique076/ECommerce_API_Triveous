package com.triveous.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.triveous.model.Users;

@Repository
public interface UserDao extends JpaRepository<Users, Integer>{

	public Users findByUserName(String username);
}
