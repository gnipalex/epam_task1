package com.epam.hnyp.shop.dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.hnyp.shop.dao.OrderItemDao;
import com.epam.hnyp.shop.model.OrderItem;

public class OrderItemDaoMySql implements OrderItemDao {

	public static final String SQL_SELECT_BY_ID = "SELECT * FROM orderitems oi WHERE oi.id = ?";
	public static final String SQL_SELECT_BY_ORDER_ID = "SELECT * FROM orderitems oi WHERE oi.order_id = ?";
	public static final String SQL_INSERT = "INSERT INTO orderitems(order_id,product_id,actual_price,count)"
			+ " VALUES(?,?,?,?)";
	
	@Override
	public OrderItem getById(int id, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private OrderItem extractOrderItem(ResultSet rs) throws SQLException {
		int id = rs.getInt("id");
		int orderId = rs.getInt("order_id");
		int productId = rs.getInt("product_id");
		long actualPrice = rs.getLong("actual_price");
		int count = rs.getInt("count");
		return new OrderItem(id, orderId, productId, actualPrice, count);
	}

	@Override
	public List<OrderItem> getAllByOrderId(int id, Connection con)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(OrderItem item, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
