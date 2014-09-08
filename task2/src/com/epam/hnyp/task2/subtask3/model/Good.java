package com.epam.hnyp.task2.subtask3.model;

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

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(id).append("\t").append(name).append("\tprice = ").append(price);
		return str.toString();
	}
}
