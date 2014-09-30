package com.epam.hnyp.task2.subtask3.model;

import java.util.Date;
import java.util.Map;

public class Order {
	private Date date;
	private String customer;
	private Map<Long, Integer> items;
	
	public Order(Date date, String customer, Map<Long, Integer> products) {
		this.date = date;
		this.customer = customer;
		this.items = products;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getCustomer() {
		return customer;
	}
	
	public Map<Long, Integer> getItems() {
		return items;
	}
	
	public int getCountOfProducts() {
		int count = 0;
		for (Integer i : items.values()) {
			count += i;
		}
		return count;
	}
}
