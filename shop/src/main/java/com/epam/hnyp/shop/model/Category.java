package com.epam.hnyp.shop.model;

public class Category {
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
	@Override
	public int hashCode() {
		return 13 * id + name.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Category)) {
			return false;
		}
		Category c = (Category)obj;
		return c.id == id && c.name.equals(name);
	}
	
	
}
