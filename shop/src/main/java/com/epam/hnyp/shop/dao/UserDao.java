package com.epam.hnyp.shop.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import com.epam.hnyp.shop.model.User;

public interface UserDao {
	int add(User user, Connection con) throws SQLException;
	void remove(int id, Connection con) throws SQLException;
	void update(User user, Connection con) throws SQLException;
	User get(int id, Connection con) throws SQLException;
	User getByLogin(String login, Connection con) throws SQLException;
	Collection<User> getAll(Connection con) throws SQLException;
}
