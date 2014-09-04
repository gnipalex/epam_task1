package com.epam.hnyp.task2.subtask3.util;

import java.util.Collection;
import java.util.Map;

public interface Store {
	/**
	 * Gets good from the store by id. If good doesn't present in the store returns null.
	 * @param id
	 * @return
	 */
	Good get(long id);
	/**
	 * Returns all goods from the store
	 * @return
	 */
	Collection<Good> getAll();
	/**
	 * Returns the price of all goods in cart. If some elements do not present in the store, they will be omitted.
	 * 
	 * @return total price
	 */
	int getPriceForAll(Map<Long, Integer> items);
}
