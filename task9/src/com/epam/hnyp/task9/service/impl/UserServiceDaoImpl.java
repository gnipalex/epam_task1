package com.epam.hnyp.task9.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import com.epam.hnyp.task9.dao.UserDao;
import com.epam.hnyp.task9.model.User;
import com.epam.hnyp.task9.service.UserService;

public class UserServiceDaoImpl implements UserService {
	private UserDao userDao;
	private TransactionManager transactionManager;

	public UserServiceDaoImpl(UserDao userDao,
			TransactionManager transactionManager) {
		this.userDao = userDao;
		this.transactionManager = transactionManager;
	}

	@Override
	public int add(final User user) {
		return transactionManager.doInTransaction(new ITransactedOperation<Integer>() {
			@Override
			public Integer execute(Connection con) throws SQLException {
				return userDao.add(user, con);
			}
		});
	}

	@Override
	public void remove(final int id) {
		transactionManager.doInTransaction(new ITransactedOperation<Void>() {
			@Override
			public Void execute(Connection con) throws SQLException {
				userDao.remove(id, con);
				return null;
			}
		});
	}

	@Override
	public void update(final User user) {
		transactionManager.doInTransaction(new ITransactedOperation<Void>() {
			@Override
			public Void execute(Connection con) throws SQLException {
				userDao.update(user, con);
				return null;
			}
		});
	}

	@Override
	public User get(final int id) {
		return transactionManager
				.doInTransaction(new ITransactedOperation<User>() {
					@Override
					public User execute(Connection con) throws SQLException {
						return userDao.get(id, con);
					}
				});
	}

	@Override
	public User getByLogin(final String email) {
		return transactionManager
				.doInTransaction(new ITransactedOperation<User>() {
					@Override
					public User execute(Connection con) throws SQLException {
						return userDao.getByLogin(email, con);
					}
				});
	}

	@Override
	public boolean userExists(String login) {
		return getByLogin(login) != null;
	}

	@Override
	public Collection<User> getAll() {
		return transactionManager
				.doInTransaction(new ITransactedOperation<Collection<User>>() {
					@Override
					public Collection<User> execute(Connection con)
							throws SQLException {
						return userDao.getAll(con);
					}
				});
	}
}
