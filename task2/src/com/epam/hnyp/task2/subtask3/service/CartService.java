package com.epam.hnyp.task2.subtask3.service;

import java.util.Map;

import com.epam.hnyp.task2.subtask3.model.Product;

public interface CartService {
	/**
	 * Adds product to cart. If good already presents, just increases count of products
	 * by one
	 * 
	 * @param g
	 */
	public void add(Product g);
	/**
	 * Removes product from cart. If amount of products in cart is greater than 1, it
	 * decreases the amount by one, else - removes product from cart.
	 * 
	 * @param g 
	 */
	public void remove(Product g);
	/**
	 * Removes product from cart by id. If amount of products in cart is greater than 1, it
	 * decreases the amount by one, else - removes product from cart.
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
