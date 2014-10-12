package com.epam.hnyp.task2.subtask3.model;

import java.io.Serializable;

public class VegetableProduct extends WeightableProduct implements Serializable {
	private static final long serialVersionUID = -2294221132926938388L;

	public VegetableProduct(long id, String name, int price, double weight) {
		super(id, name, price, weight);
	}
	
	public VegetableProduct() {
	}

}
