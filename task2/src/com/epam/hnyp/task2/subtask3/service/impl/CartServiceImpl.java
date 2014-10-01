package com.epam.hnyp.task2.subtask3.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.service.CartService;

public class CartServiceImpl implements CartService {
	private Map<Long, Integer> cart = new HashMap<>();

	@Override
	public void add(Product g) {
		if (cart.containsKey(g.getId())) {
			int val = cart.get(g.getId());
			cart.put(g.getId(), ++val);
		} else {
			cart.put(g.getId(), 1);
		}
	}

	@Override
	public void remove(Product g) {
		remove(g.getId());
	}
	
	@Override
	public void remove(Long id){
		if (!cart.containsKey(id)) {
			return;
		}
		int val = cart.get(id);
		if (val > 1) {
			cart.put(id, --val);
		} else {
			cart.remove(id);
		}
	}

	@Override
	public int size() {
		int size = 0;
		for (Entry<Long, Integer> e : cart.entrySet()) {
			size += e.getValue();
		}
		return size;
	}	
	
	/**
	 * @return unmodifiable Map of ids and count
	 */
	@Override
	public Map<Long, Integer> getAllItems() {
		return Collections.unmodifiableMap(cart);
	}

	@Override
	public void clear() {
		this.cart.clear();
	}
}
