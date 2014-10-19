package com.epam.hnyp.task9.service;

import java.util.Collection;

import com.epam.hnyp.task9.model.User;

public interface UserService {
	boolean add(User user);
	boolean remove(long id);
	User get(long id);
	User getByEmail(String email);
	boolean userExists(String email);
	Collection<User> getAll();
}
