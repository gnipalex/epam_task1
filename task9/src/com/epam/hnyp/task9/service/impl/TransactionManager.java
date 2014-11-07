package com.epam.hnyp.task9.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.epam.hnyp.task9.service.ServiceLayerException;

public class TransactionManager {
	private static final Logger LOG = Logger.getLogger(TransactionManager.class);
	private DataSource dataSource;
	
	public TransactionManager(DataSource ds) {
		this.dataSource = ds;
	}

	/**
	 * 
	 * @param op operation T to do in transaction
	 * @return result T of operation
	 * @throws ServiceLayerException if any inner {@link SQLException} occurred
	 */
	public <T> T doInTransaction(ITransactedOperation<T> op) {
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
			throw new ServiceLayerException(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				LOG.error(e);
			}
		}
	}
}
