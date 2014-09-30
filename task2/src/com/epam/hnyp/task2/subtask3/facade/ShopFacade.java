package com.epam.hnyp.task2.subtask3.facade;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.epam.hnyp.task2.subtask3.model.Order;
import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.util.Cart;

public interface ShopFacade {
	/**
	 * Adds product by id to current cart and adds it to the popular list
	 * @param id id of product
	 * @return true if product is added to the cart
	 */
	boolean addToCart(long id);
	/**
	 * Makes an order using products from current cart. Clears the cart if success
	 * @param customer
	 * @param date
	 * @return
	 */
	boolean makeOrder(String customer, Date date);
	
	/**
	 * Gets map of product id and count of product
	 * @return
	 */
	Map<Long, Integer> getCartItems();
	
	/**
	 * Clears current cart
	 */
	void clearCart();
	
	/**
	 * Returns summary count of items in cart
	 * @return
	 */
	int cartSize();
	
	/**
	 * Removes product from cart
	 * @param id
	 */
	void removeFromCart(Long id);
	/**
	 * Evaluates price for cart
	 * @return
	 */
	int getPriceForCart();
	/**
	 * Gets the collection of last popular products
	 * @return
	 */
	Collection<Product> getPopularProducts();
	
	/**
	 * Returns collection of all orders
	 * @return
	 */
	Collection<Order> getAllOrders();
	
	/**
	 * Finds order of specified period
	 * @param from
	 * @param to
	 * @return
	 */
	Collection<Order> getOrdersOfPeriod(Date from, Date to);
	
	/**
	 * Finds nearest order to specified date
	 * @param date
	 * @return
	 */
	Order getNearestOrder(Date date);
	
	/**
	 * Evaluates price for order
	 * @param o
	 * @return
	 */
	int getPriceForOrder(Order o);
	
	/**
	 * Finds product by specified id
	 * @param id
	 * @return
	 */
	Product getProductById(long id);
	
	/**
	 * Gets all products
	 * @return
	 */
	Collection<Product> getAllProducts();
	
	/**
	 * Adds new product
	 * @param g
	 * @return
	 */
	boolean addNewProduct(Product g);

}
