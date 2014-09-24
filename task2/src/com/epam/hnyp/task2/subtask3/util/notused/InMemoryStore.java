package com.epam.hnyp.task2.subtask3.util.notused;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

import com.epam.hnyp.task2.subtask3.model.CerealProduct;
import com.epam.hnyp.task2.subtask3.model.DrinkProduct;
import com.epam.hnyp.task2.subtask3.model.Product;
import com.epam.hnyp.task2.subtask3.model.Order;
import com.epam.hnyp.task2.subtask3.model.SweetProduct;
import com.epam.hnyp.task2.subtask3.model.VegetableProduct;
import com.epam.hnyp.task2.subtask3.model.WeightableProduct;

public class InMemoryStore implements Store {
	private Map<Long, Product> goods = new LinkedHashMap<>();
	{
		long id = 1;
		Product g1 = new CerealProduct(id++, "sugar", 12, 2);
		goods.put(g1.getId(), g1);
		Product g2 = new DrinkProduct(id++, "milk", 11, 1);
		goods.put(g2.getId(), g2);
		Product g3 = new SweetProduct(id++, "vafles artek", 30, 0.2, "chocolate");
		goods.put(g3.getId(), g3);
		Product g4 = new VegetableProduct(id++, "potato", 4, 1);
		goods.put(g4.getId(), g4);
		Product g5 = new WeightableProduct(id++, "bread", 5, 0.7);
		goods.put(g5.getId(), g5);
		Product g6 = new DrinkProduct(id++, "cola", 12, 2);
		goods.put(g6.getId(), g6);
		Product g7 = new Product(id++, "sigarettes", 20);
		goods.put(g7.getId(), g7);
		Product g8 = new Product(id++, "butter", 15);
		goods.put(g8.getId(), g8);
	}

	private NavigableMap<Date, Order> orders = new TreeMap<>();

	private InMemoryStore() {
	}

	private static InMemoryStore singleTone;

	public static synchronized InMemoryStore getInstance() {
		if (singleTone == null) {
			singleTone = new InMemoryStore();
		}
		return singleTone;
	}

	@Override
	public Product get(long id) {
		return goods.get(id);
	}

	@Override
	public Collection<Product> getAll() {
		return Collections.unmodifiableCollection(goods.values());
	}

	@Override
	public int getPriceForAll(Map<Long, Integer> items) {
		int cost = 0;
		for (Entry<Long, Integer> e : items.entrySet()) {
			Product g = get(e.getKey());
			if (g != null) {
				cost += g.getPrice() * e.getValue();
			}
		}
		return cost;
	}

	@Override
	public boolean saveOrder(Order order) {
		orders.put(order.getDate(), order);
		return true;
	}

	@Override
	public Collection<Order> getAllOrders() {
		return orders.values();
	}

	@Override
	public Collection<Order> getOrdersOfPeriod(Date from, Date to) {
		return orders.subMap(from, to).values();
	}

	@Override
	public Order getNearest(Date date) {
		Date up_key = orders.ceilingKey(date);
		Date down_key = orders.floorKey(date);
		if (up_key == null && down_key == null) {
			return null;
		}
		if (up_key == null) {
			return orders.get(down_key);
		}
		if (down_key == null) {
			return orders.get(up_key);
		}
		Long up_time = up_key.getTime();
		Long down_time = down_key.getTime();
		Long date_time = date.getTime();
		Order order = null;
		
		if (up_time - date_time <= date_time - down_time) {
			order = orders.get(up_key);
		} else {
			order = orders.get(down_key);
		}
		return order;
	}


}
