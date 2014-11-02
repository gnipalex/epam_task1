package com.epam.hnyp.task9.service;

import java.sql.SQLException;
import java.util.Collection;

import com.epam.hnyp.task9.model.User;

public interface UserService {
	void add(User user) throws SQLException;
	void remove(int id) throws SQLException;
	void update(User user) throws SQLException;
	User get(int id) throws SQLException;
	User getByLogin(String email) throws SQLException;
	boolean userExists(String login) throws SQLException;
	Collection<User> getAll() throws SQLException;
}
