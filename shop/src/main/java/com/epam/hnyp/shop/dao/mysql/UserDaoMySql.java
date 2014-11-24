package com.epam.hnyp.shop.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.epam.hnyp.shop.dao.UserDao;
import com.epam.hnyp.shop.model.Role;
import com.epam.hnyp.shop.model.User;

public class UserDaoMySql implements UserDao {
	private static final Logger LOG = Logger.getLogger(UserDaoMySql.class);

	public static final String SQL_SELECT_ALL = "SELECT * FROM users";
	public static final String SQL_SELECT_BY_ID = "SELECT * FROM users u WHERE u.id = ?";
	public static final String SQL_SELECT_BY_LOGIN = "SELECT * FROM users u WHERE u.login = ?";
	public static final String SQL_INSERT = "INSERT INTO users(name,lastName,password,login,receiveLetters,avatarFile,role) "
			+ "VALUES(?,?,?,?,?,?,?)";
	public static final String SQL_REMOVE_BY_ID = "DELETE FROM users u WHERE u.id = ?";
	public static final String SQL_UPDATE_BY_ID = "UPDATE users u "
			+ "SET name = ?,lastName = ?,password = ?,login = ?,receiveLetters = ?,avatarFile = ?, role = ? "
			+ "WHERE u.id = ?";

	@Override
	public int add(User user, Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_INSERT,
				PreparedStatement.RETURN_GENERATED_KEYS)) {
			int index = 1;
			prst.setString(index++, user.getName());
			prst.setString(index++, user.getLastName());
			prst.setString(index++, user.getPassword());
			prst.setString(index++, user.getLogin());
			prst.setBoolean(index++, user.isReceiveLetters());
			if (user.getAvatarFile() != null) {
				prst.setString(index++, user.getAvatarFile());
			} else {
				prst.setNull(index++, Types.NULL);
			}
			prst.setString(index++, user.getRole().name());
			LOG.debug(prst);
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
	public void remove(int id, Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_REMOVE_BY_ID)) {
			prst.setInt(1, id);
			LOG.debug(prst);
			prst.executeUpdate();
		}
	}

	@Override
	public void update(User user, Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_UPDATE_BY_ID)) {
			int index = 1;
			prst.setString(index++, user.getName());
			prst.setString(index++, user.getLastName());
			prst.setString(index++, user.getPassword());
			prst.setString(index++, user.getLogin());
			prst.setBoolean(index++, user.isReceiveLetters());
			if (user.getAvatarFile() != null) {
				prst.setString(index++, user.getAvatarFile());
			} else {
				prst.setNull(index++, Types.NULL);
			}
			prst.setString(index++, user.getRole().name());
			prst.setInt(index++, user.getId());
			LOG.debug(prst);
			prst.executeUpdate();
		}
	}

	@Override
	public User get(int id, Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_SELECT_BY_ID)) {
			LOG.debug(prst);
			ResultSet rs = prst.executeQuery();
			User u = null;
			if (rs.next()) {
				u = extractUser(rs);
			}
			rs.close();
			return u;
		}
	}

	private User extractUser(ResultSet rs) throws SQLException {
		User u = new User();
		u.setAvatarFile(rs.getString("avatarFile"));
		u.setId(rs.getInt("id"));
		u.setLastName(rs.getString("lastName"));
		u.setLogin(rs.getString("login"));
		u.setName(rs.getString("name"));
		u.setPassword(rs.getString("password"));
		u.setReceiveLetters(rs.getBoolean("receiveLetters"));
		u.setRole(Role.valueOf(rs.getString("role")));
		return u;
	}

	@Override
	public User getByLogin(String login, Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_SELECT_BY_LOGIN)) {
			prst.setString(1, login);
			LOG.debug(prst);
			ResultSet rs = prst.executeQuery();
			User u = null;
			if (rs.next()) {
				u = extractUser(rs);
			}
			rs.close();
			return u;
		}
	}

	@Override
	public Collection<User> getAll(Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_SELECT_ALL)) {
			LOG.debug(prst);
			ResultSet rs = prst.executeQuery();
			Collection<User> users = new ArrayList<>();
			if (rs.next()) {
				User u = extractUser(rs);
				users.add(u);
			}
			rs.close();
			return users;
		}
	}

}