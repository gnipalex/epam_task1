package com.epam.hnyp.task9.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class TransactionManager {
	private static final Logger LOG = Logger.getLogger(TransactionManager.class);
	private DataSource dataSource;
	
	public TransactionManager() {}
	
	public TransactionManager(DataSource ds) {
		this.dataSource = ds;
	}

	public <T> T doInTransaction(ITransactedOperation<T> op) throws SQLException {
		Connection con = null;
		T result = null;
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			result = op.execute(con);
			con.commit();
			return result;
		} catch (SQLException e) {
			LOG.error(e);
			try {
				con.rollback(); 
			} catch (SQLException se) {
				LOG.error(e);
			}
			throw e;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				LOG.error(e);
			}
		}
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}
