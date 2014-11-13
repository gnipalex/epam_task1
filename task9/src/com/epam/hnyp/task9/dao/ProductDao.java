package com.epam.hnyp.task9.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import com.epam.hnyp.task9.form.ProductFilterBean;
import com.epam.hnyp.task9.model.Product;

public interface ProductDao {
	int add(Product p, Connection con) throws SQLException;
	void update(Product p, Connection con) throws SQLException;
	void remove(int id, Connection con) throws SQLException;
	Product get(int id, Connection con) throws SQLException;
	Collection<Product> getAll(Connection con) throws SQLException;
	Collection<Product> getByFilter(ProductFilterBean filter, Connection con) throws SQLException;
	int getCountWithoutLimit(ProductFilterBean filter, Connection con) throws SQLException;
}
