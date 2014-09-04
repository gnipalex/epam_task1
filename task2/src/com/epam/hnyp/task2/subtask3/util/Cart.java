package com.epam.hnyp.task2.subtask3.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Cart {
	public static int LAST_MAX_COUNT = 5;
	// key - id of good, val - count
	private Map<Long, Integer> cart = new HashMap<>();

	//container for last added elements
	private static Map<Long, Good> lastGoods = new LinkedHashMap<Long, Good>(
			16, 0.75f, true) {
		@Override
		protected boolean removeEldestEntry(
				java.util.Map.Entry<Long, Good> eldest) {
			return size() > LAST_MAX_COUNT;
		}
	};

	/**
	 * Adds good to cart. If good already presents, just increases count of good
	 * by one
	 * 
	 * @param g
	 */
	public void add(Good g) {
		if (cart.containsKey(g.getId())) {
			int val = cart.get(g.getId());
			cart.put(g.getId(), ++val);
		} else {
			cart.put(g.getId(), 1);
		}
		lastGoods.put(g.getId(), g);
	}

	/**
	 * Gets last goods added to cart
	 * 
	 * @return immutable collection of goods
	 */
	public static Collection<Good> getLastGoods() {
		return Collections.unmodifiableCollection(lastGoods.values());
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
		int val = cart.get(g.getId());
		if (val > 1) {
			cart.put(g.getId(), --val);
		}
	}
	
	/**
	 * Removes all nonexistent items from cart.
	 * @param store - link to the store to check items
	 */
	public void removeAllNonExistent(Store store) {
		List<Long> keysToRemove = new ArrayList<>();
		for (Entry<Long, Integer> e : cart.entrySet()) {
			if (store.get(e.getKey()) == null) {
				keysToRemove.add(e.getKey());
			}
		} 
		for (Long l : keysToRemove) {
			cart.remove(l);
		}
	}

	/**
	 * Total count of elements in cart
	 * @return
	 */
	public int size() {
		int size = 0;
		for (Entry<Long, Integer> e : cart.entrySet()) {
			size += e.getValue();
		}
		return size;
	}	
	
	/**
	 * Returns Map<K,V> where K - item id and V - amount of item 
	 * @return
	 */
	public Map<Long, Integer> getAllItems() {
		return Collections.unmodifiableMap(cart);
	}
}
