package com.epam.hnyp.task2.subtask3.model;

import java.util.Formatter;

public class WeightableProduct extends Product {
	private double weight;
	
	public WeightableProduct(long id, String name, int price, double weight) {
		super(id, name, price);
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\tweight = " + weight;
	}
	
	@Override
	protected String printOtherColumn() {
		Formatter fmt = new Formatter();
		fmt.format("%1$20s", "weight=" + weight);
		return fmt.toString();
	}
}
