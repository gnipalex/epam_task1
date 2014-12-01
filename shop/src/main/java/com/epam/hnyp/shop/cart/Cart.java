package com.epam.hnyp.shop.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.epam.hnyp.shop.model.Order;
import com.epam.hnyp.shop.model.OrderItem;
import com.epam.hnyp.shop.model.Product;

public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<Integer, CartItem<Product>> items = new LinkedHashMap<>();

	/**
	 * @param p
	 * @return incremented count of item in cart
	 */
	public int add(Product p) {
		CartItem<Product> item = items.get(p.getId());
		if (item != null) {
			item.increment();
		} else {
			item = new CartItem<Product>(p);
			items.put(p.getId(), item);
		}
		return item.getCount();
	}
	
	/**
	 * @param id
	 * @return decremented count of item in cart, if element not found returns -1
	 */
	public int remove(int id) {
		CartItem<Product> item = items.get(id);
		if (item != null) {
			item.decrement();
			if (item.getCount() < 1) {
				items.remove(id);
			}
			return item.getCount();
		}
		return -1;
	}
	
	/**
	 * removes all occurrences of item
	 * @param id
	 */
	public void removeAll(int id) {
		items.remove(id);
	}

	/**
	 * 
	 * @return total count of items in cart
	 */
	public int getTotalCount() {
		int count = 0;
		for (CartItem<Product> item : items.values()) {
			count += item.getCount();
		}
		return count;
	}

	/**
	 * clears cart
	 */
	public void clear() {
		items.clear();
	}

	/**
	 * 
	 * @return total price of all items in cart
	 */
	public long getTotalPrice() {
		long price = 0;
		for (CartItem<Product> item : items.values()) {
			price += item.getCount() * item.getItem().getPrice();
		}
		return price;
	}

	/**
	 * Prepares order model
	 * @return
	 * @throws CartStateException
	 */
	public Order prepareOrder() throws CartStateException {
		if (items.isEmpty()) {
			throw new CartStateException("cart is empty");
		}
		Order order = new Order();
		List<OrderItem> orderItems = new ArrayList<>();
		for (CartItem<Product> cItem : items.values()) {
			orderItems.add(new OrderItem(0, 0, cItem.getItem().getId(), cItem
					.getItem().getPrice(), cItem.getCount()));
		}
		order.setItems(orderItems);
		return order;
	}

	public Collection<CartItem<Product>> getAllItems() {
		return Collections.unmodifiableCollection(items.values());
	}

	public static class CartStateException extends Exception {
		private static final long serialVersionUID = 1L;

		public CartStateException() {
			super();
		}

		public CartStateException(String arg0) {
			super(arg0);
		}
	}

	/**
	 * Represents element of cart
	 * @author Alex
	 *
	 * @param <E>
	 */
	public static class CartItem<E> {
		private int count = 1;
		private E item;

		public CartItem(E item) {
			this.item = item;
		}

		public int getCount() {
			return count;
		}

		public E getItem() {
			return item;
		}
		
		public void setItem(E item) {
			this.item = item;
		}

		private void increment() {
			count++;
		}

		private void decrement() {
			count--;
		}
	}
}
