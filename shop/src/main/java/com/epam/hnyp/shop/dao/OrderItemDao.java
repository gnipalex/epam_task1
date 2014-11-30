package com.epam.hnyp.shop.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.hnyp.shop.model.OrderItem;

public interface OrderItemDao {
	OrderItem getById(int id, Connection con) throws SQLException;
	List<OrderItem> getAllByOrderId(int orderId, Connection con) throws SQLException;
	void add(OrderItem item, Connection con) throws SQLException;
	void addAll(List<OrderItem> items, int orderId, Connection con) throws SQLException;
	int getItemsCountForOrder(int orderId, Connection con) throws SQLException;
	long getTotalPriceForOrder(int orderId, Connection con) throws SQLException;
}
