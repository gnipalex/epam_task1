package com.epam.hnyp.task9.repo;

import java.util.Collection;

import com.epam.hnyp.task9.model.User;

public interface UserRepo {
	boolean add(User user);
	boolean remove(long id);
	User get(long id);
	User getByEmail(String email);
	Collection<User> getAll();
}
