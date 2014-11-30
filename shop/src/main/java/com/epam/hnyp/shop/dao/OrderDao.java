package com.epam.hnyp.shop.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.epam.hnyp.shop.model.Order;
import com.epam.hnyp.shop.model.OrderStatus;

public interface OrderDao {
	List<Order> getAll(Connection con) throws SQLException;
	Order getById(int id, Connection con) throws SQLException;
	int add(Order order, Connection con) throws SQLException;
	void update(Order order, Connection con) throws SQLException;
	void remove(int id, Connection con) throws SQLException;
	List<Order> getByUserId(int id, Connection con) throws SQLException;
	void updateStatus(int id, OrderStatus status, Connection con) throws SQLException;
	List<Order> getByPeriod(Date from, Date to, Connection con) throws SQLException;
}
