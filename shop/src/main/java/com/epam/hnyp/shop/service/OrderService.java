package com.epam.hnyp.shop.service;

import java.util.Date;
import java.util.List;

import com.epam.hnyp.shop.model.Order;
import com.epam.hnyp.shop.model.OrderStatus;

public interface OrderService {
	/**
	 * @throws ServiceLayerException if any i/o error occurred
	 */
	int createOrder(Order order);
	/**
	 * @throws ServiceLayerException if any i/o error occurred
	 */
	List<Order> getAllOrders();
	/**
	 * @throws ServiceLayerException if any i/o error occurred
	 */
	Order getOrder(int id);
	/**
	 * @throws ServiceLayerException if any i/o error occurred
	 */
	void updateOrderStatus(int id, OrderStatus status);
	/**
	 * @throws ServiceLayerException if any i/o error occurred
	 */
	void removeOrder(int id);
	/**
	 * @throws ServiceLayerException if any i/o error occurred
	 */
	List<Order> getOrdersByPeriod(Date from, Date to);
	/**
	 * @throws ServiceLayerException if any i/o error occurred
	 */
	List<Order> getOrdersByUserId(int id);	
}
