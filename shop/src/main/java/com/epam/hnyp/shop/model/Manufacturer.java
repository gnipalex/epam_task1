package com.epam.hnyp.shop.model;

import java.io.Serializable;

public class Manufacturer implements Serializable {

	private static final long serialVersionUID = -9162087635449050995L;
	
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
