package com.epam.hnyp.task2.subtask3.service;

import java.util.Collection;
import java.util.Date;

import com.epam.hnyp.task2.subtask3.model.Good;
import com.epam.hnyp.task2.subtask3.util.Cart;

public interface ShopService {
	/**
	 * Adds good by id to current cart and adds it to the popular list
	 * @param id id of good
	 * @return true if good is added to the cart
	 */
	boolean addToCart(long id);
	/**
	 * Makes an order using goods from current cart. Clears the cart if success
	 * @param customer
	 * @param date
	 * @return
	 */
	boolean makeOrder(String customer, Date date);
	/**
	 * Gets current cart
	 * @return
	 */
	Cart getCurrentCart();
	/**
	 * Gets the collection of last popular goods
	 * @return
	 */
	Collection<Good> getPopularGoods();
	
	/**
	 * Evaluates price for cart
	 * @return
	 */
	int getPriceForCart();
}
