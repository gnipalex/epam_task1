package com.epam.hnyp.shop.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.epam.hnyp.shop.dao.OrderDao;
import com.epam.hnyp.shop.dao.OrderItemDao;
import com.epam.hnyp.shop.dao.ProductDao;
import com.epam.hnyp.shop.model.Order;
import com.epam.hnyp.shop.model.OrderItem;
import com.epam.hnyp.shop.model.OrderStatus;
import com.epam.hnyp.shop.service.OrderService;

public class OrderServiceDaoImpl implements OrderService {

	private OrderDao orderDao;
	private OrderItemDao orderItemDao;
	private ProductDao productDao;
	private TransactionManager transactionManager;
	
	public OrderServiceDaoImpl(OrderDao orderDao, OrderItemDao orderItemDao, ProductDao productDao,
			TransactionManager transactionManager) {
		this.orderDao = orderDao;
		this.orderItemDao = orderItemDao;
		this.productDao = productDao;
		this.transactionManager = transactionManager;
	}

	@Override
	public int createOrder(final Order order) {
		return transactionManager.doInTransaction(new ITransactedOperation<Integer>() {
			@Override
			public Integer execute(Connection con) throws SQLException {
				int id = orderDao.add(order, con);
				orderItemDao.addAll(order.getItems(), id, con);
				return id;
			}
		});
	}

	@Override
	public List<Order> getAllOrders() {
		return transactionManager.doInTransaction(new ITransactedOperation<List<Order>>() {
			@Override
			public List<Order> execute(Connection con) throws SQLException {
				List<Order> orders = orderDao.getAll(con);
				if (orders != null) {
					for (Order o : orders) {
						//o.setItems(orderItemDao.getAllByOrderId(o.getId(), con));
						o.setTotalPrice(orderItemDao.getTotalPriceForOrder(o.getId(), con));
						o.setItemCount(orderItemDao.getItemsCountForOrder(o.getId(), con));
					}
				}
				return orders;
			}
		});
	}

	@Override
	public void updateOrderStatus(final int id, final OrderStatus status) {
		transactionManager.doInTransaction(new ITransactedOperation<Void>() {
			@Override
			public Void execute(Connection con) throws SQLException {
				orderDao.updateStatus(id, status, con);
				return null;
			}
		});
	}

	@Override
	public void removeOrder(final int id) {
		transactionManager.doInTransaction(new ITransactedOperation<Void>() {
			@Override
			public Void execute(Connection con) throws SQLException {
				orderDao.remove(id, con);
				return null;
			}
		});
	}

	@Override
	public List<Order> getOrdersByPeriod(final Date from, final Date to) {
		return transactionManager.doInTransaction(new ITransactedOperation<List<Order>>() {
			@Override
			public List<Order> execute(Connection con) throws SQLException {
				List<Order> orders = orderDao.getByPeriod(from, to, con);
				if (orders != null) {
					for (Order o : orders) {
						//o.setItems(orderItemDao.getAllByOrderId(o.getId(), con));
						o.setTotalPrice(orderItemDao.getTotalPriceForOrder(o.getId(), con));
						o.setItemCount(orderItemDao.getItemsCountForOrder(o.getId(), con));
					}
				}
				return orders;
			}
		});
	}

	@Override
	public List<Order> getOrdersByUserId(final int id) {
		return transactionManager.doInTransaction(new ITransactedOperation<List<Order>>() {
			@Override
			public List<Order> execute(Connection con) throws SQLException {
				List<Order> orders = orderDao.getByUserId(id, con);
				if (orders != null) {
					for (Order o : orders) {
						//o.setItems(orderItemDao.getAllByOrderId(o.getId(), con));
						o.setTotalPrice(orderItemDao.getTotalPriceForOrder(o.getId(), con));
						o.setItemCount(orderItemDao.getItemsCountForOrder(o.getId(), con));
					}
				}
				return orders;
			}
		});
	}

	@Override
	public Order getOrder(final int id) {		
		return transactionManager.doInTransaction(new ITransactedOperation<Order>() {
			@Override
			public Order execute(Connection con) throws SQLException {
				Order order = orderDao.getById(id, con);
				if (order != null) {
					order.setItems(orderItemDao.getAllByOrderId(order.getId(), con));
					if (order.getItems() != null) {
						for (OrderItem oi : order.getItems()) {
							oi.setProduct(productDao.get(oi.getProductId(), con));
						}
					}
					order.setTotalPrice(orderItemDao.getTotalPriceForOrder(order.getId(), con));
					order.setItemCount(orderItemDao.getItemsCountForOrder(order.getId(), con));
				}
				return order;
			}
		});
	}

}
