package com.epam.hnyp.task9.model;

public class Manufacturer {
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
		return 17*id + name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Manufacturer)) {
			return false;
		}
		Manufacturer m = (Manufacturer)obj;
		return m.id == id && m.name.equals(name);
	}
}
