package com.epam.hnyp.shop.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.hnyp.shop.model.Order;

public interface OrderDao {
	List<Order> getAll(Connection con) throws SQLException;
	Order getById(int id, Connection con) throws SQLException;
	int add(Order order, Connection con) throws SQLException;
	void update(Order order, Connection con) throws SQLException;
	void remove(int id, Connection con) throws SQLException;
}
