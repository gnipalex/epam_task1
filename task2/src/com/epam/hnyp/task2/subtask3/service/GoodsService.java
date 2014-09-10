package com.epam.hnyp.task2.subtask3.service;

import java.util.Collection;
import java.util.Map;

import com.epam.hnyp.task2.subtask3.model.Good;

public interface GoodsService {
	/**
	 * Gets good from the store by id. If good doesn't present in the store returns null.
	 * @param id
	 * @return
	 */
	Good get(long id);
	
	/**
	 * Returns collection of all goods from the store
	 * @return Collection
	 */
	Collection<Good> getAll();
	
	/**
	 * Adds new good to the store
	 * @param g
	 * @return true if good was added
	 */
	boolean add(Good g);
	
	/**
	 * Returns the price of all goods in cart. If some elements do not present in the store, they will be omitted.
	 * 
	 * @return total price
	 */
	int getPriceForAll(Map<Long, Integer> items);
}
