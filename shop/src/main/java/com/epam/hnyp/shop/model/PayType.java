package com.epam.hnyp.shop.model;

public enum PayType {
	
	CREDIT("credit"), CASH("cash");
	
	private final String type;

	private PayType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
}
