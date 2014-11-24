package com.epam.hnyp.shop.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.epam.hnyp.shop.dao.ManufacturerDao;
import com.epam.hnyp.shop.model.Manufacturer;

public class ManufacturerDaoMySql implements ManufacturerDao {

	public static final String SQL_SELECT_ALL = "SELECT * FROM manufacturers";
	public static final String SQL_SELECT_BY_ID = "SELECT * FROM manufacturers m WHERE m.id = ?";
	public static final String SQL_REMOVE_BY_ID = "DELETE FROM manufacturers m WHERE m.id = ?";
	public static final String SQL_UPDATE_BY_ID = "UPDATE manufacturers m SET m.name = ? WHERE m.id = ?";
	public static final String SQL_INSERT = "INSERT INTO manufacturers(name) VALUES(?)";
	
	@Override
	public int add(Manufacturer c, Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS)){
			prst.setString(1, c.getName());
			prst.executeUpdate();
			ResultSet rs = prst.getGeneratedKeys();
			int generatedId = 0;
			if (rs.next()) {
				generatedId = rs.getInt(1);
			}
			rs.close();
			return generatedId;
		}
	}

	@Override
	public Manufacturer get(int id, Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_SELECT_BY_ID)) {
			prst.setInt(1, id);
			ResultSet rs = prst.executeQuery();
			Manufacturer v = null;
			if (rs.next()) {
				v = extractVendor(rs);
			}
			rs.close();
			return v;
		}
	}
	
	private Manufacturer extractVendor(ResultSet rs) throws SQLException {
		Manufacturer v = new Manufacturer();
		v.setId(rs.getInt("id"));
		v.setName(rs.getString("name"));
		return v;
	}

	@Override
	public void update(Manufacturer c, Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_UPDATE_BY_ID)) {
			int index = 1;
			prst.setString(index++, c.getName());
			prst.setInt(index++, c.getId());
			prst.executeUpdate();
		}
	}

	@Override
	public void remove(int id, Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_REMOVE_BY_ID)) {
			prst.setInt(1, id);
			prst.executeUpdate();
		}
	}

	@Override
	public Collection<Manufacturer> getAll(Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_SELECT_ALL)) {
			ResultSet rs = prst.executeQuery();
			Collection<Manufacturer> vendors = new ArrayList<>();
			while (rs.next()) {
				Manufacturer v = extractVendor(rs);
				vendors.add(v);
			}
			rs.close();
			return vendors;
		}
	}

}
