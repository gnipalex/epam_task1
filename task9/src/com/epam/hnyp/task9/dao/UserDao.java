package com.epam.hnyp.task9.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import com.epam.hnyp.task9.model.User;

public interface UserDao {
	void add(User user, Connection con) throws SQLException;
	void remove(int id, Connection con) throws SQLException;
	void update(User user, Connection con) throws SQLException;
	User get(int id, Connection con) throws SQLException;
	User getByLogin(String login, Connection con) throws SQLException;
	Collection<User> getAll(Connection con) throws SQLException;
}
