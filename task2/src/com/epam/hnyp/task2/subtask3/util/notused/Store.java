package com.epam.hnyp.task2.subtask3.util.notused;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.epam.hnyp.task2.subtask3.model.Good;
import com.epam.hnyp.task2.subtask3.model.Order;

public interface Store {
	/**
	 * Gets good from the store by id. If good doesn't present in the store returns null.
	 * @param id
	 * @return
	 */
	Good get(long id);
	/**
	 * Returns all goods from the store
	 * @return good
	 */
	Collection<Good> getAll();
	/**
	 * Returns the price of all goods in cart. If some elements do not present in the store, they will be omitted.
	 * 
	 * @return total price
	 */
	int getPriceForAll(Map<Long, Integer> items);
	
	/**
	 * Saves new order
	 * @param date
	 * @param order
	 * @return true if order saved
	 */
	boolean saveOrder(Order order);
	
	/**
	 * Gets collection of all orders.
	 * @return unmodifiable map of orders
	 */
	Collection<Order> getAllOrders();
	
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
