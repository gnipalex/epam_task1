package com.epam.hnyp.task4.subtask2;

public class ProductImpl implements IProduct {
	private int id;
	private String name;
	private int price;
	
	public ProductImpl() {
	}
	
	public ProductImpl(int id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public int getPrice() {
		return price;
	}

}
