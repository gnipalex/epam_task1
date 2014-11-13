package com.epam.hnyp.task9.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.epam.hnyp.task9.dao.CategoryDao;
import com.epam.hnyp.task9.model.Category;

public class CategoryDaoMySql implements CategoryDao{

	public static final String SQL_SELECT_ALL = "SELECT * FROM categories";
	public static final String SQL_SELECT_BY_ID = "SELECT * FROM categories c WHERE c.id = ?";
	public static final String SQL_REMOVE_BY_ID = "DELETE FROM categories c WHERE c.id = ?";
	public static final String SQL_UPDATE_BY_ID = "UPDATE categories c SET c.name = ? WHERE c.id = ?";
	public static final String SQL_INSERT = "INSERT INTO categories(name) VALUES(?)";
	
	@Override
	public int add(Category c, Connection con) throws SQLException {
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
	public Category get(int id, Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_SELECT_BY_ID)) {
			prst.setInt(1, id);
			ResultSet rs = prst.executeQuery();
			Category cat = null;
			if (rs.next()) {
				cat = extractCategory(rs);
			}
			rs.close();
			return cat;
		}
	}
	
	private Category extractCategory(ResultSet rs) throws SQLException {
		Category c = new Category();
		c.setId(rs.getInt("id"));
		c.setName(rs.getString("name"));
		return c;
	}

	@Override
	public void update(Category c, Connection con) throws SQLException {
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
	public Collection<Category> getAll(Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_SELECT_ALL)) {
			ResultSet rs = prst.executeQuery();
			Collection<Category> categories = new ArrayList<>();
			if (rs.next()) {
				Category c = extractCategory(rs);
				categories.add(c);
			}
			rs.close();
			return categories;
		}
	}

}
