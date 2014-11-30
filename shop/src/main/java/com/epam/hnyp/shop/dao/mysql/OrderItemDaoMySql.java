package com.epam.hnyp.shop.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.hnyp.shop.dao.OrderItemDao;
import com.epam.hnyp.shop.model.OrderItem;

public class OrderItemDaoMySql implements OrderItemDao {

	public static final String SQL_SELECT_BY_ID = "SELECT * FROM orderitems oi WHERE oi.id = ?";
	public static final String SQL_SELECT_BY_ORDER_ID = "SELECT * FROM orderitems oi WHERE oi.order_id = ?";
	public static final String SQL_INSERT = "INSERT INTO orderitems(order_id,product_id,actual_price,count)"
			+ " VALUES(?,?,?,?)";
	public static final String SQL_SELECT_SUM_PRICE_BY_ORDER_ID = "SELECT SUM(oi.actual_price * oi.count) FROM orderitems oi WHERE oi.order_id = ?";
	public static final String SQL_SELECT_COUNT_BY_ORDER_ID = "SELECT SUM(oi.count) FROM orderitems oi WHERE oi.order_id = ?";
	
	@Override
	public OrderItem getById(int id, Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_SELECT_BY_ID)) {
			prst.setInt(1, id);
			ResultSet rs = prst.executeQuery();
			OrderItem item = null;
			if (rs.next()) {
				item = extractOrderItem(rs);
			}
			rs.close();
			return item;
		}
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
		try (PreparedStatement prst = con.prepareStatement(SQL_SELECT_BY_ORDER_ID)) {
			prst.setInt(1, id);
			ResultSet rs = prst.executeQuery();
			List<OrderItem> items = new ArrayList<>();
			while (rs.next()) {
				items.add(extractOrderItem(rs));
			}
			rs.close();
			return items;
		}
	}

	@Override
	public void add(OrderItem item, Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_INSERT)) {
			fillPreparedStatement(prst, item, item.getOrderId());
			prst.executeUpdate();
		}
	}
	
	@Override
	public void addAll(List<OrderItem> items, int orderId, Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_INSERT)) {
			for (OrderItem item : items) {
				fillPreparedStatement(prst, item, orderId);
				prst.addBatch();
			}
			prst.executeBatch();
		}
	}
	
	private void fillPreparedStatement(PreparedStatement prst, OrderItem item, int orderId) throws SQLException {
		int index = 1;
		prst.setInt(index++, orderId);
		prst.setInt(index++, item.getProductId());
		prst.setLong(index++, item.getActualPrice());
		prst.setInt(index++, item.getCount());
	}

	@Override
	public int getItemsCountForOrder(int orderId, Connection con)
			throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_SELECT_COUNT_BY_ORDER_ID)) {
			prst.setInt(1, orderId);
			ResultSet rs = prst.executeQuery();
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			return count;
		}
	}

	@Override
	public long getTotalPriceForOrder(int orderId, Connection con)
			throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_SELECT_COUNT_BY_ORDER_ID)) {
			prst.setInt(1, orderId);
			ResultSet rs = prst.executeQuery();
			long price = 0;
			if (rs.next()) {
				price = rs.getLong(1);
			}
			rs.close();
			return price;
		}
	}

}
