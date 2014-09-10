package com.epam.hnyp.task2.subtask3.repo;

import java.util.Collection;
import java.util.Date;

import com.epam.hnyp.task2.subtask3.model.Order;

public interface OrderRepo {
	/**
	 * Saves new order
	 * @param date
	 * @param order
	 * @return true if order saved
	 */
	boolean add(Order order);
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
}
