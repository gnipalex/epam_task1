package com.epam.hnyp.task9.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import com.epam.hnyp.task9.model.Manufacturer;

public interface ManufacturerDao {
	int add(Manufacturer c, Connection con) throws SQLException;
	Manufacturer get(int id, Connection con) throws SQLException;
	void update(Manufacturer c, Connection con) throws SQLException;
	void remove(int id, Connection con) throws SQLException;
	Collection<Manufacturer> getAll(Connection con) throws SQLException;
}
