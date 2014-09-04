package com.epam.hnyp.task2.subtask3.util;

public class Good {
	private String name;
	private int price;
	private long id;
	
	public Good(long id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public long getId() {
		return id;
	}
}
