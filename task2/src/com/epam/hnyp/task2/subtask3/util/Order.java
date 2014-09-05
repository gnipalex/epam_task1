package com.epam.hnyp.task2.subtask3.util;

import java.util.Date;


public class Order {
	private Date date;
	private long id;
	private String customer;
	private Cart cart;
	
	public Order(Date date, long id, String customer, Cart cart) {
		this.date = date;
		this.id = id;
		this.customer = customer;
		this.cart = cart;
	}
	
	public Cart getCart() {
		return cart;
	}
	
	public Date getDate() {
		return date;
	}
	
	public long getId() {
		return id;
	}
	
	public String getCustomer() {
		return customer;
	}
}
