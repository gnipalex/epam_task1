package com.epam.hnyp.shop.model;

import java.io.Serializable;

public class Category implements Serializable {
	
	private static final long serialVersionUID = -4803698322746238361L;
	
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
