package com.epam.hnyp.task9.repo;

import java.util.Collection;

import com.epam.hnyp.task9.model.User;

public interface UserRepo {
	/**
	 * @throws IllegalArgumentException if user with specified login already exists
	 */
	void add(User user);
	/**
	 * @throws IllegalArgumentException if user with specified id not found
	 */
	void remove(long id);
	void update(User user);
	User get(long id);
	User getByLogin(String login);
	Collection<User> getAll();
}
