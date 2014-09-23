package com.epam.hnyp.task2.subtask3.model;

import java.util.Date;
import java.util.Map;

public class Order {
	private Date date;
	private String customer;
	private Map<Long, Integer> items;
	
	public Order(Date date, String customer, Map<Long, Integer> goods) {
		this.date = date;
		this.customer = customer;
		this.items = goods;
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

	public void setItems(Map<Long, Integer> items) {
		this.items = items;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	public int getCountOfProducts() {
		int count = 0;
		for (Integer i : items.values()) {
			count += i;
		}
		return count;
	}
}
