package com.epam.hnyp.task9.dao;

import java.sql.SQLException;
import java.util.Collection;

import com.epam.hnyp.task9.model.Product;
import com.mysql.jdbc.Connection;

public interface ProductDao {
	int add(Product p, Connection con) throws SQLException;
	void update(Product p, Connection con) throws SQLException;
	void remove(int id, Connection con) throws SQLException;
	Product get(int id, Connection con) throws SQLException;
	Collection<Product> getAll(Connection con) throws SQLException;
	Collection<Product> getByCriteria(Criteria criteria, Connection con) throws SQLException;
}
