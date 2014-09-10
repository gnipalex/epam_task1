package com.epam.hnyp.task2.subtask3.service;

import java.util.Collection;
import java.util.Date;

import com.epam.hnyp.task2.subtask3.model.Order;
import com.epam.hnyp.task2.subtask3.util.Cart;

public interface OrderService {
	/**
	 * Gets collection of all orders.
	 * @return unmodifiable map of orders
	 */
	Collection<Order> getAll();
	/**
	 * Gets all orders of specified period
	 * @param from date start of period
	 * @param to date end of period
	 * @return orders List of specified period
	 */
	Collection<Order> getOrdersOfPeriod(Date from, Date to);
	/**
	 * Gets the nearest Order by date
	 * @param date
	 * @return order or null if no orders
	 */
	Order getNearest(Date date);
	
	/**
	 * Makes order using specified cart, name of customer and date
	 * @param cart
	 * @param customer
	 * @param date
	 * @return true if order created
	 */
	boolean makeOrder(Cart cart, String customer, Date date);
	
}
