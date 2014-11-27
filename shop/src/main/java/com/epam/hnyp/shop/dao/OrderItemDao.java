package com.epam.hnyp.shop.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.hnyp.shop.model.OrderItem;

public interface OrderItemDao {
	OrderItem getById(int id, Connection con) throws SQLException;
	List<OrderItem> getAllByOrderId(int id, Connection con) throws SQLException;
	void add(OrderItem item, Connection con) throws SQLException;
}
