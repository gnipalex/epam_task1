package com.epam.hnyp.task2.subtask3.util;

import java.sql.Date;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

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
	 */
	void saveOrder(Date date, Order order);
	
	/**
	 * Gets map of all orders.
	 * @return unmodifiable map of orders
	 */
	Map<Date, Order> getAllOrders();
}
