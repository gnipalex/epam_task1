package com.epam.hnyp.task9.model;

public enum OrderStatus {
	ACCEPTED("accepted"), 
	CONFIRMED("confirmed"), 
	FORMING("forming"), 
	SENT("sent"), 
	COMPLETED("completed"), 
	CANCELED("canceled");
	
	private final String status;

	private OrderStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	
}
