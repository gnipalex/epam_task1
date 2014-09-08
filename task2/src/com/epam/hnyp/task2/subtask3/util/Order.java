package com.epam.hnyp.task2.subtask3.util;

import java.util.Date;


public class Order {
	private Date date;
	private String customer;
	private Cart cart;
	
	public Order(Date date, String customer, Cart cart) {
		this.date = date;
		this.customer = customer;
		this.cart = cart;
	}
	
	public Cart getCart() {
		return cart;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getCustomer() {
		return customer;
	}
}
