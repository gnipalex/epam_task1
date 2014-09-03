package com.epam.hnyp.task2.subtask3;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Cart {
	// key - id of good, val - count
	private Map<Long, Integer> cart = new HashMap<>();
	// link to the store
	private Store store;

	public Cart(Store store) {
		this.store = store;
	}

	public void add(Good g) {
		if (cart.containsKey(g.getId())) {
			int val = cart.get(g.getId());
			cart.put(g.getId(), ++val);
			return;
		}
		cart.put(g.getId(), 1);
	}

	public long cost() {
		long cost = 0;
		for (Entry<Long, Integer> e : cart.entrySet()) {
			Good g = store.get(e.getKey());
			cost += g.getPrice() * e.getValue();
		}
		return cost;
	}

	public void clear() {
		cart.clear();
	}

	/**
	 * Removes good from cart. If amount of good in cart is greater than 1, it
	 * decreases the amount by one, else - removes good from cart.
	 * 
	 * @param g
	 */
	public void remove(Good g) {
		if (!cart.containsKey(g.getId())) {
			return;
		}
		int count = cart.get(g.getId());
		if (count > 1) {
			
		}
			

	}

	public int size() {
		int size = 0;
		for (Entry<Long, Integer> e : cart.entrySet()) {
			size += e.getValue();
		}
		return size;
	}
}
