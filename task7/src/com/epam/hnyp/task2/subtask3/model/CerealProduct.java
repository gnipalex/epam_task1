package com.epam.hnyp.task2.subtask3.model;

import java.io.Serializable;

public class CerealProduct extends WeightableProduct implements Serializable {
	private static final long serialVersionUID = 4469179991544962051L;

	public CerealProduct(long id, String name, int price, double weight) {
		super(id, name, price, weight);
	}
	
	public CerealProduct() {
	}

}
