package com.epam.hnyp.shop.model;

public enum DeliveryType {
	SELFDELIVERY("self delivery"), HOMEDELIVERY("home delivery");
	
	private final String delivery;
	
	private DeliveryType(String delivery) {
		this.delivery = delivery;
	}

	public String getDelivery() {
		return delivery;
	}
	
}
