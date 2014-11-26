package com.epam.hnyp.shop.model;

public enum OrderStatus {
	ACCEPTED("accepted"), CONFIRMED("confirmed"), FORMING("forming"), SENT(
			"sent"), COMPLETED("completed"), CANCELED("canceled");

	private final String status;

	private OrderStatus(final String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
