package com.epam.hnyp.task9.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;

import com.epam.hnyp.task9.dao.UserDao;
import com.epam.hnyp.task9.model.User;

public class UserDaoMySql implements UserDao {

	public static final String SQL_SELECT_ALL = "SELECT * FROM users";
	public static final String SQL_SELECT_BY_ID = "SELECT * FROM users u WHERE u.id = ?";
	public static final String SQL_SELECT_BY_LOGIN = "SELECT * FROM users u WHERE u.login = ?";
	public static final String SQL_INSERT = "INSERT INTO users(name,lastName,password,login,receiveLetters,avatarFile,role_id) " +
			"VALUES(?,?,?,?,?,?,?)";
	public static final String SQL_REMOVE_BY_ID = "DELETE FROM users u WHERE u.id = ?";
	public static final String SQL_UPDATE_BY_ID = "UPDATE users u " +
			"SET name = ?,lastName = ?,password = ?,login = ?,receiveLetters = ?,avatarFile = ?, role_id = ? " + 
			"WHERE u.id = ?";
			
	
	@Override
	public void add(User user, Connection con) throws SQLException {
		PreparedStatement prst = con.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
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
		prst.setInt(index++, user.getRole().id());
		// http://javatrain.wordpress.com/2011/07/08/install-maven-at-eclipse-2/
		
	}

	@Override
	public void remove(int id, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(User user, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User get(int id, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getByLogin(String login, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<User> getAll(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
