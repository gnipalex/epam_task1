package com.epam.hnyp.task9.repo;

import java.util.Collection;

import com.epam.hnyp.task9.model.User;

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
	Collection<User> getAll();
}
