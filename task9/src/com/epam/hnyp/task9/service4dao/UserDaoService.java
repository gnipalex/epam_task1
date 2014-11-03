package com.epam.hnyp.task9.service4dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import com.epam.hnyp.task9.dao.UserDao;
import com.epam.hnyp.task9.model.User;
import com.epam.hnyp.task9.service.UserService;

public class UserDaoService implements UserService  {
	private UserDao userDao;
	private TransactionManager transactionManager;
	
	public UserDaoService() {
	}
	
	public UserDaoService(UserDao userDao, TransactionManager transactionManager) {
		this.userDao = userDao;
		this.transactionManager = transactionManager;
	}
	
	@Override
	public void add(final User user) throws SQLException {
		transactionManager.doInTransaction(new ITransactedOperation<Void>() {
			@Override
			public Void execute(Connection con) throws SQLException {
				userDao.add(user, con);
				return null;
			}
		});
	}

	@Override
	public void remove(final int id) throws SQLException {
		transactionManager.doInTransaction(new ITransactedOperation<Void>() {
			@Override
			public Void execute(Connection con) throws SQLException {
				userDao.remove(id, con);
				return null;
			}
		});
	}

	@Override
	public void update(final User user) throws SQLException {
		transactionManager.doInTransaction(new ITransactedOperation<Void>() {
			@Override
			public Void execute(Connection con) throws SQLException {
				userDao.update(user, con);
				return null;
			}
		});
	}

	@Override
	public User get(final int id) throws SQLException {
		return transactionManager.doInTransaction(new ITransactedOperation<User>() {
			@Override
			public User execute(Connection con) throws SQLException {
				return userDao.get(id, con);
			}
		});
	}

	@Override
	public User getByLogin(final String email) throws SQLException {
		return transactionManager.doInTransaction(new ITransactedOperation<User>() {
			@Override
			public User execute(Connection con) throws SQLException {
				return userDao.getByLogin(email, con);
			}
		});
	}

	@Override
	public boolean userExists(String login) throws SQLException {
		return getByLogin(login) != null;
	}

	@Override
	public Collection<User> getAll() throws SQLException {
		return transactionManager.doInTransaction(new ITransactedOperation<Collection<User>>() {
			@Override
			public Collection<User> execute(Connection con) throws SQLException {
				return userDao.getAll(con);
			}
		});
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
