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

	public void add(Product p) {
		CartItem<Product> item = items.get(p.getId());
		if (item != null) {
			item.increment();
		} else {
			items.put(p.getId(), new CartItem<Product>(p));
		}
	}

	public void remove(int id) {
		CartItem<Product> item = items.get(id);
		if (item != null) {
			item.decrement();
			if (item.getCount() < 1) {
				items.remove(id);
			}
		}
	}

	public int getTotalCount() {
		int count = 0;
		for (CartItem<Product> item : items.values()) {
			count += item.getCount();
		}
		return count;
	}

	public void clear() {
		items.clear();
	}

	public long getTotalPrice() {
		long price = 0;
		for (CartItem<Product> item : items.values()) {
			price += item.getCount() * item.getItem().getPrice();
		}
		return price;
	}

//	public Order createOrder() throws CartStateException {
//		if (items.isEmpty()) {
//			throw new CartStateException("cart is empty");
//		}
//		Order order = new Order();
//		List<OrderItem> orderItems = new ArrayList<>();
//		for (CartItem<Product> cItem : items.values()) {
//			
//			orderItems.add(new OrderItem(0, 0, cItem.getItem().getId(), cItem
//					.getItem().getPrice(), cItem.getCount()));
//		}
//		order.setItems(orderItems);
//		
//	}

	public Collection<CartItem<Product>> getAllItems() {
		return Collections.unmodifiableCollection(items.values());
	}

	public static class CartStateException extends Exception {
		public CartStateException() {
			super();
		}

		public CartStateException(String arg0) {
			super(arg0);
		}
	}

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

		private void increment() {
			count++;
		}

		private void decrement() {
			count--;
		}
	}
}
