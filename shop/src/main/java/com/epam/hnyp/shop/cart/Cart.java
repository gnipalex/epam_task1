package com.epam.hnyp.shop.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.epam.hnyp.shop.model.Order;
import com.epam.hnyp.shop.model.OrderItem;
import com.epam.hnyp.shop.model.Product;

public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<Integer, CartItem<Product>> items = new LinkedHashMap<>();

	/**
	 * Creates empty cart
	 */
	public Cart() {
	}
	
	/**
	 * Creates copy of existing cart
	 * @param cart
	 */
	public Cart(Cart cart) {
		for (Entry<Integer, CartItem<Product>> e : cart.items.entrySet()) {
			CartItem<Product> ci = new CartItem<Product>(e.getValue().item);
			ci.count = e.getValue().count;
			this.items.put(e.getKey(), ci);
		}
	}

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
	 * Gets price for separate item (for all occurrences)
	 * @param id item id
	 * @return
	 */
	public long getItemPrice(int id) {
		long price = 0;
		CartItem<Product> item = items.get(id);
		if (item != null) {
			price = item.getCount() * item.getItem().getPrice();
		}
		return price;
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
	 * @throws CartStateException if cart is empty
	 */
	public Order prepareOrder() throws CartStateException {
		if (items.isEmpty()) {
			throw new CartStateException("cart is empty");
		}
		Order order = new Order();
		List<OrderItem> orderItems = new ArrayList<>();
		for (CartItem<Product> cItem : items.values()) {
			OrderItem oi = new OrderItem(0, 0, cItem.getItem().getId(), cItem.getItem().getPrice(), cItem.getCount());
			oi.setProduct(cItem.getItem());
			orderItems.add(oi);
		}
		order.setItems(orderItems);
		order.setItemCount(getTotalCount());
		order.setTotalPrice(getTotalPrice());
		return order;
	}
	
	/**
	 * Collection of all items in cart
	 * @return
	 */
	public Collection<CartItem<Product>> getItems() {
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
	public static class CartItem<E> implements Serializable {
		private static final long serialVersionUID = 1L;
		
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
