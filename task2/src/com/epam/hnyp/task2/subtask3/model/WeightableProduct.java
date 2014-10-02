package com.epam.hnyp.task2.subtask3.model;

import java.io.Serializable;
import java.util.Formatter;


public class WeightableProduct extends Product implements Serializable {
	private static final long serialVersionUID = 2262341991941044041L;

	private double weight;
	
	public WeightableProduct() {
	}
	
	public WeightableProduct(long id, String name, int price, double weight) {
		super(id, name, price);
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	@ProductSetterAnnotation(friendlyMessage = "PROD_WEIGHTABLE_WEIGHT", type = Double.class)
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
		String s = String.format("weight=%1$.2f", weight);
		fmt.format("%1$20s", s);
		return fmt.toString();
	}
}
