package com.epam.hnyp.shop.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.epam.hnyp.shop.dao.OrderDao;
import com.epam.hnyp.shop.model.DeliveryType;
import com.epam.hnyp.shop.model.Order;
import com.epam.hnyp.shop.model.OrderStatus;
import com.epam.hnyp.shop.model.PayType;

public class OrderDaoMySql implements OrderDao {

	public static final String SQL_SELECT_ALL = "SELECT * FROM orders";
	public static final String SQL_SELECT_BY_ID = "SELECT * FROM orders o WHERE o.id = ?";
	public static final String SQL_INSERT = "INSERT INTO orders(status,description,date,user_id,delivery,pay_type,address) "
			+ "VALUES(?,?,?,?,?,?,?)";
	public static final String SQL_UPDATE_BY_ID = "UPDATE orders SET status=?, description=?, date=?, user_id=?, "
			+ "delivery=?, pay_type=?, address=? WHERE id=?";
	public static final String SQL_REMOVE_BY_ID = "DELETE FROM orders o WHERE o.id=?";
	public static final String SQL_SELECT_BY_USER_ID = "SELECT * FROM orders o WHERE o.user_id = ?";
	public static final String SQL_UPDATE_STATUS_BY_ID = "UPDATE orders SET status=? WHERE id = ?";
	public static final String SQL_SELECT_BY_PERIOD = "SELECT * FROM orders o WHERE o.date >= ? AND o.date <= ?";

	@Override
	public List<Order> getAll(Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_SELECT_ALL)) {
			ResultSet rs = prst.executeQuery();
			List<Order> orders = new ArrayList<Order>();
			while (rs.next()) {
				orders.add(extractOrder(rs));
			}
			rs.close();
			return orders;
		}
	}

	private Order extractOrder(ResultSet rs) throws SQLException {
		Order order = new Order();
		order.setDate(rs.getDate("date"));
		try {
			order.setDeliveryType(DeliveryType.valueOf(rs.getString("delivery")));
		} catch (Exception e) {
		}
		order.setDescription(rs.getString("description"));
		order.setId(rs.getInt("id"));
		order.setUserId(rs.getInt("user_id"));
		try {
			order.setStatus(OrderStatus.valueOf(rs.getString("status")));
		} catch (Exception ex) {
		}
		try {
			order.setPayType(PayType.valueOf(rs.getString("pay_type")));
		} catch (Exception ex) {
		}
		return order;
	}

	@Override
	public Order getById(int id, Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_SELECT_BY_ID)) {
			prst.setInt(1, id);
			ResultSet rs = prst.executeQuery();
			Order order = null;
			if (rs.next()) {
				order = extractOrder(rs);
			}
			rs.close();
			return order;
		}
	}

	@Override
	public int add(Order order, Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
			int index = 1;
			prst.setString(index++, order.getStatus().toString());
			prst.setString(index++, order.getDescription());
			prst.setTimestamp(index++, new Timestamp(order.getDate().getTime()));
			prst.setInt(index++, order.getUserId());
			prst.setString(index++, order.getDeliveryType().toString());
			prst.setString(index++, order.getPayType().toString());
			prst.setString(index++, order.getAddress());
			prst.executeUpdate();
			ResultSet rs = prst.getGeneratedKeys();
			int generatedId = 0;
			if (rs.next()) {
				generatedId = rs.getInt(1);
			}
			rs.close();
			return generatedId;
		}

	}

	@Override
	public void update(Order order, Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_UPDATE_BY_ID)) {
			int index = 1;
			prst.setString(index++, order.getStatus().toString());
			prst.setString(index++, order.getDescription());
			prst.setTimestamp(index++, new Timestamp(order.getDate().getTime()));
			prst.setInt(index++, order.getUserId());
			prst.setString(index++, order.getDeliveryType().toString());
			prst.setString(index++, order.getPayType().toString());
			prst.setString(index++, order.getAddress());
			prst.setInt(index++, order.getId());
			prst.executeUpdate();
		}

	}

	@Override
	public void remove(int id, Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_REMOVE_BY_ID)) {
			prst.setInt(1, id);
			prst.executeUpdate();
		}
	}

	@Override
	public List<Order> getByUserId(int id, Connection con) throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_SELECT_BY_USER_ID)) {
			ResultSet rs = prst.executeQuery();
			List<Order> orders = new ArrayList<Order>();
			while (rs.next()) {
				orders.add(extractOrder(rs));
			}
			rs.close();
			return orders;
		}
	}

	@Override
	public void updateStatus(int id, OrderStatus status, Connection con)
			throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_UPDATE_STATUS_BY_ID)) {
			int index = 1;
			prst.setString(index++, status.toString());
			prst.setInt(index++, id);
			prst.executeUpdate();
		}
		
	}

	@Override
	public List<Order> getByPeriod(Date from, Date to, Connection con)
			throws SQLException {
		try (PreparedStatement prst = con.prepareStatement(SQL_SELECT_BY_PERIOD)) {
			int index = 1;
			prst.setTimestamp(index++, new Timestamp(from.getTime()));
			prst.setTimestamp(index++, new Timestamp(to.getTime()));
			ResultSet rs = prst.executeQuery();
			List<Order> orders = new ArrayList<Order>();
			while (rs.next()) {
				orders.add(extractOrder(rs));
			}
			rs.close();
			return orders;
		}
	}

}
