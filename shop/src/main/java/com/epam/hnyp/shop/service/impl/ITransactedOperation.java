package com.epam.hnyp.shop.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

public interface ITransactedOperation<E> {
	E execute(Connection con) throws SQLException;
}
