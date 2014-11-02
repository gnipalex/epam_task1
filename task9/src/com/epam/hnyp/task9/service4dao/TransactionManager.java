package com.epam.hnyp.task9.service4dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class TransactionManager {
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
			//log
			try {
				con.rollback(); 
			} catch (SQLException se) {
				//log warn
			}
			throw e;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				//log warn
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
