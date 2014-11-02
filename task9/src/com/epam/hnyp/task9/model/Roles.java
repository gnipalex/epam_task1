package com.epam.hnyp.task9.model;

public enum Roles {
	CUSTOMER(1), ADMIN(2);
	
	private int id;

	private Roles(int id) {
		this.id = id;
	}
	
	public int id() {
		return id;
	}
}
