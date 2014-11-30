package com.epam.hnyp.shop.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.epam.hnyp.shop.model.Manufacturer;

public interface ManufacturerDao {
	int add(Manufacturer c, Connection con) throws SQLException;
	Manufacturer get(int id, Connection con) throws SQLException;
	void update(Manufacturer c, Connection con) throws SQLException;
	void remove(int id, Connection con) throws SQLException;
	List<Manufacturer> getAll(Connection con) throws SQLException;
}
