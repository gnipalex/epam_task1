package com.epam.hnyp.task2.subtask3.model;

import java.io.Serializable;

public class CerealGood extends WeightableGood implements Serializable {
	private static final long serialVersionUID = -8947618810900700217L;

	public CerealGood(long id, String name, int price, double weight) {
		super(id, name, price, weight);
	}
	
}
