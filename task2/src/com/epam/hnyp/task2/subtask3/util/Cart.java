package com.epam.hnyp.task2.subtask3.util;

import java.util.Map;

import com.epam.hnyp.task2.subtask3.model.Good;

public interface Cart {
	/**
	 * Adds good to cart. If good already presents, just increases count of good
	 * by one
	 * 
	 * @param g
	 */
	public void add(Good g);
	/**
	 * Removes good from cart. If amount of good in cart is greater than 1, it
	 * decreases the amount by one, else - removes good from cart.
	 * 
	 * @param g 
	 */
	public void remove(Good g);
	/**
	 * Removes good from cart by id. If amount of good in cart is greater than 1, it
	 * decreases the amount by one, else - removes good from cart.
	 * @param id
	 */
	public void remove(Long id);
	/**
	 * Total count of elements in cart
	 * @return
	 */
	public int size();
	/**
	 * Returns Map<K,V> where K - item id and V - amount of item 
	 * @return
	 */
	public Map<Long, Integer> getAllItems();
	
	/**
	 * Removes all elements from cart
	 */
	public void clear();
}
