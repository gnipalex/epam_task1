package com.epam.hnyp.task9.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.epam.hnyp.task9.dao.UserDao;
import com.epam.hnyp.task9.model.User;
import com.epam.hnyp.task9.service.ServiceLayerException;
import com.epam.hnyp.task9.service.UserService;

public class UserServiceDaoImpl implements UserService {
	private static final Logger LOG = Logger
			.getLogger(UserServiceDaoImpl.class);
	
	private UserDao userDao;
	private TransactionManager transactionManager;

	public UserServiceDaoImpl() {
	}

	public UserServiceDaoImpl(UserDao userDao,
			TransactionManager transactionManager) {
		this.userDao = userDao;
		this.transactionManager = transactionManager;
	}

	@Override
	public void add(final User user) {
		try {
			transactionManager
					.doInTransaction(new ITransactedOperation<Void>() {
						@Override
						public Void execute(Connection con) throws SQLException {
							userDao.add(user, con);
							return null;
						}
					});
		} catch (SQLException e) {
			LOG.error(e);
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public void remove(final int id) {
		try {
			transactionManager
					.doInTransaction(new ITransactedOperation<Void>() {
						@Override
						public Void execute(Connection con) throws SQLException {
							userDao.remove(id, con);
							return null;
						}
					});
		} catch (SQLException e) {
			LOG.error(e);
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public void update(final User user) {
		try {
			transactionManager
					.doInTransaction(new ITransactedOperation<Void>() {
						@Override
						public Void execute(Connection con) throws SQLException {
							userDao.update(user, con);
							return null;
						}
					});
		} catch (SQLException e) {
			LOG.error(e);
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public User get(final int id) {
		try {
			return transactionManager
					.doInTransaction(new ITransactedOperation<User>() {
						@Override
						public User execute(Connection con) throws SQLException {
							return userDao.get(id, con);
						}
					});
		} catch (SQLException e) {
			LOG.error(e);
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public User getByLogin(final String email) {
		try {
			return transactionManager
					.doInTransaction(new ITransactedOperation<User>() {
						@Override
						public User execute(Connection con) throws SQLException {
							return userDao.getByLogin(email, con);
						}
					});
		} catch (SQLException e) {
			LOG.error(e);
			throw new ServiceLayerException(e);
		}
	}

	@Override
	public boolean userExists(String login) {
		return getByLogin(login) != null;
	}

	@Override
	public Collection<User> getAll() {
		try {
			return transactionManager
					.doInTransaction(new ITransactedOperation<Collection<User>>() {
						@Override
						public Collection<User> execute(Connection con)
								throws SQLException {
							return userDao.getAll(con);
						}
					});
		} catch (SQLException e) {
			LOG.error(e);
			throw new ServiceLayerException(e);
		}
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public TransactionManager getTransactionManager() {
		return transactionManager;
	}

	public void setTransactionManager(TransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

}
