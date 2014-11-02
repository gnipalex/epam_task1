package com.epam.hnyp.task9.service4dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ITransactedOperation<E> {
	E execute(Connection con) throws SQLException;
}
