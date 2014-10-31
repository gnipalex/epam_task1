package com.epam.hnyp.task9.service;

import java.util.Collection;

import com.epam.hnyp.task9.model.User;

public interface UserService {
	void add(User user);
	void remove(long id);
	User get(long id);
	User getByLogin(String email);
	boolean userExists(String login);
	Collection<User> getAll();
}
