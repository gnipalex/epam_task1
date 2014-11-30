package com.epam.hnyp.shop.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.hnyp.shop.form.ProductFilterBean;
import com.epam.hnyp.shop.model.Product;

public interface ProductDao {
	int add(Product p, Connection con) throws SQLException;
	void update(Product p, Connection con) throws SQLException;
	void remove(int id, Connection con) throws SQLException;
	Product get(int id, Connection con) throws SQLException;
	List<Product> getAll(Connection con) throws SQLException;
	List<Product> getByFilter(ProductFilterBean filter, Connection con) throws SQLException;
	int getCountWithoutLimit(ProductFilterBean filter, Connection con) throws SQLException;
}
