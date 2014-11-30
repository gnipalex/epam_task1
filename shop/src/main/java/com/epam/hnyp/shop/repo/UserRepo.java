package com.epam.hnyp.shop.repo;

import java.util.List;

import com.epam.hnyp.shop.model.User;

public interface UserRepo {
	/**
	 * @return generated id for user 
	 * @throws IllegalArgumentException if user with specified login already exists
	 */
	int add(User user);
	/**
	 * @throws IllegalArgumentException if user with specified id not found
	 */
	void remove(int id);
	void update(User user);
	User get(int id);
	User getByLogin(String login);
	List<User> getAll();
}
