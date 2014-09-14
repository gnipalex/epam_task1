package com.epam.hnyp.task2.subtask3.model;

import java.io.Serializable;

public class VegetableGood extends WeightableGood implements Serializable {
	private static final long serialVersionUID = -5108849535745255520L;

	public VegetableGood(long id, String name, int price, double weight) {
		super(id, name, price, weight);
	}

}
